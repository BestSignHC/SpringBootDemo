package com.hecheng.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MockitoDemo3 {

    @Test
    @DisplayName("验证方法调用")
    public void testMockVerify() {
        Foo realFoo = new Foo();
        Bar mockBar = mock(Bar.class);
        realFoo.fun4(mockBar);

        // bar1调用过一次
        verify(mockBar, times(1)).bar1();

        // bar2调用过两次次
        verify(mockBar, times(2)).bar2();

        // bar3没有调用过
        verify(mockBar, never()).bar3();
    }
}
