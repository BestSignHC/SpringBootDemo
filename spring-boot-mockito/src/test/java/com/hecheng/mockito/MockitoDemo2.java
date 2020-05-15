package com.hecheng.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoDemo2 {
    @Test
    @DisplayName("模拟检测方法入参")
    public void testArgCap() {
        Foo mockFoo = mock(Foo.class);

        // 参数捕捉器
        ArgumentCaptor captureString = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor captureInteger = ArgumentCaptor.forClass(Integer.class);
        mockFoo.fun1("1", 2);

        verify(mockFoo).fun1((String)captureString.capture(), (Integer) captureInteger.capture());

        Assertions.assertEquals("1", captureString.getValue());
        Assertions.assertEquals(2, captureInteger.getValue());
    }

    @Test
    @DisplayName("模拟检测方法入参2")
    public void testArgCap2() {
        Foo mockFoo = mock(Foo.class);

        // 参数捕捉器
        ArgumentCaptor<Bar> captor = ArgumentCaptor.forClass(Bar.class);
        mockFoo.fun2(new Bar(1, "2"));

        verify(mockFoo).fun2(captor.capture());

        Assertions.assertEquals(1, captor.getValue().getId());
        Assertions.assertEquals("2", captor.getValue().getName());
    }
}
