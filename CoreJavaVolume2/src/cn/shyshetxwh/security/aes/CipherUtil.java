package cn.shyshetxwh.security.aes;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CipherUtil {
    /**
     * 利用cipher将一个输入文件的字节转化为新的字节并存入到输出文件中
     * @param in
     * @param out
     * @param cipher
     */
    public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        //得到密码块的大小
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        //最后的一个数据块大小
        int inLength=0;
        boolean done=false;
        while(!done)
        {
            inLength = in.read(inBytes);
            if (inLength==blockSize)
            {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes,0,outLength);
            }
            else
            {
                done=true;
            }
        }
        if (inLength>0)outBytes=cipher.doFinal(inBytes,0,inLength);
        else outBytes=cipher.doFinal();
        out.write(outBytes);

    }
}
