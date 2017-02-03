    lfs.attributes(filepath [, aname]) 获取路径指定属性
    lfs.chdir(path) 改变当前工作目录，成功返回true，失败返回nil加上错误信息
    lfs.currentdir 获取当前工作目录，成功返回路径，失败为nil加上错误信息
    lfs.dir(path) 返回一个迭代器（function）和一个目录（userdata），每次迭代器都会返回一个路径，直到不是文件目录为止，则迭代器返回nil
    lfs.lock(filehandle, mode[, start[, length]])
    lfs.mkdir(dirname)  创建一个新目录
    lfs.rmdir(dirname) 删除一个已存在的目录，成功返回true，失败返回nil加上错误信息
