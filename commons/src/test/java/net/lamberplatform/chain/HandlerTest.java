package net.lamberplatform.chain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class HandlerTest {

    @InjectMocks
    private TestHandler handler;

    @Spy
    private TestHandler next;

    @Test
    public void testMaybeNext_NoNextHandler() {
        assertThrows(CallHandlerException.class, handler::maybeNext);
    }

    @Test
    public void testMaybeNext_HasNextHandler() {
        HandlerUnitTestTool.invokeConfigureNextHandler(handler, next);
        TestHandler result = handler.maybeNext();
        assertEquals(next, result);
        assertThrows(CallHandlerException.class, result::maybeNext);
    }

    private static class TestHandler extends Handler<TestHandler> {
    }
}
