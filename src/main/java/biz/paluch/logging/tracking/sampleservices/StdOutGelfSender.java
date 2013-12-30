package biz.paluch.logging.tracking.sampleservices;

import biz.paluch.logging.gelf.intern.GelfMessage;
import biz.paluch.logging.gelf.intern.GelfSender;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 30.12.13 15:50
 */
public class StdOutGelfSender implements GelfSender
{
    @Override
    public boolean sendMessage(GelfMessage message)
    {
        System.out.println(message.toJson());
        return true;
    }
    @Override
    public void close()
    {

    }
}
