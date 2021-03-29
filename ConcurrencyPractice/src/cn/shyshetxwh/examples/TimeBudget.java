package cn.shyshetxwh.examples;

import java.util.*;
import java.util.concurrent.*;

/**
 * FileName: TimeBudget
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 19:34
 */
public class TimeBudget {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    private class QuoteTask implements Callable<TravelQuote> {
        private final TravelCompany company;
        private final TravelInfo info;

        public QuoteTask(TravelCompany company, TravelInfo info) {
            this.company = company;
            this.info = info;
        }

        TravelQuote getFailureQuote(Throwable t) {
            return null;
        }

        TravelQuote getTimeoutQuote(CancellationException e) {
            return null;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(info);
        }
    }


    public List<TravelQuote> getRankedTravelQuotes(TravelInfo info, Set<TravelCompany> companies
            , Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company, info));
        }
        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIterator.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }
        Collections.sort(quotes, ranking);
        return quotes;
    }

    interface TravelCompany {
        TravelQuote solicitQuote(TravelInfo travelInfo) throws Exception;
    }

    interface TravelQuote {
    }

    interface TravelInfo {
    }

}
