package com.hecheng.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoDemo1 {

    @Test
    @DisplayName("模拟Mock对象行为")
    public void testMock() {
        List mockList = mock(List.class);

        when(mockList.add(1)).thenReturn(true);
        when(mockList.size()).thenReturn(1);

        Assertions.assertTrue(mockList.add(1));
        Assertions.assertEquals(mockList.size(), 1);
        Assertions.assertFalse(mockList.add(2));

        // 期望抛出空指针
        when(mockList.add(null)).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> mockList.add(null));

        // 多次期望 第一次调用返回1，后续每一次都返回2
        when(mockList.get(anyInt())).thenReturn(1).thenReturn(2);
        Assertions.assertEquals(1, mockList.get(0));
        Assertions.assertEquals(2, mockList.get(1));
        Assertions.assertEquals(2, mockList.get(99));
    }

    @Test
    @DisplayName("校验方法执行")
    public void testMock2() {
        List mockList = mock(List.class);

        mockList.add(1);
        mockList.clear();

        verify(mockList).add(1);
//        verify(mockList).add(2);
        verify(mockList).clear();
    }

    @Test
    @DisplayName("自定义参数匹配")
    public void testMock3() {
        List mockList = mock(List.class);

        // 所有合法参数都返回ture
        when(mockList.contains(argThat(new IsValid()))).thenReturn(true);

        // 合法参数内，遵循上诉定义，返回true
        Assertions.assertTrue(mockList.contains(0));
        Assertions.assertTrue(mockList.contains(1));

        // 不在定义的合法参数内，返回false
        Assertions.assertFalse(mockList.contains(7));
        Assertions.assertFalse(mockList.contains("aa"));
    }


    // 定义小于5的 Integer为合法数据
    private class IsValid implements ArgumentMatcher {

        @Override
        public boolean matches(Object argument) {
            return argument instanceof Integer && (Integer) argument < 5;
        }
    }
}
