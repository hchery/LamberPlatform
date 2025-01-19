package net.lamberplatform.chain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@ExtendWith(MockitoExtension.class)
public class HandlerManagerTest {

    @InjectMocks
    private TestManager manager;

    @Mock
    private ApplicationContext ctx;

    @Test
    public void testFetchHead() {
        when(ctx.getBean(TestHandler.class)).thenReturn(new TestHandler());
        assertThrows(CallHandlerException.class, manager::fetchHead);
        manager.setApplicationContext(ctx);
        TestHandler handler = manager.fetchHead();
        assertNotNull(handler);
        assertThrows(CallHandlerException.class, handler::maybeNext);
    }

    private static class TestHandler extends Handler<TestHandler> {}

    private static class TestManager extends HandlerManager<TestHandler> {

        @Override
        protected void applyHandlers(HandlerApply<TestHandler> apply) {
            apply.$(TestHandler.class);
        }
    }
}
