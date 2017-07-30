package com.gl.order.online.inter;

import com.gl.order.online.exception.OnlineException;
import com.gl.order.online.vo.SimpleOrderVO;

public interface SimpleOrderOnlineService {
    SimpleOrderVO submit(SimpleOrderVO reqOrder) throws OnlineException;
}
