#!/usr/local/bin/lua

print("executable lua script")
print("Hello LUA")


print("info")
print("package.path=" .. package.path)

msg=[[
练习table
]]

print(msg)
print(#msg)


local fruits={"apple","banana","orange","pear"}
for k,v in pairs(fruits) do
    print(k .. ":" .. v)
end



msg = [[
练习迭代器
]]

print(msg)

function singleIterator()
    if currentNum == nil then
       currentNum = 0;
    end

    if currentNum < 10 then
       currentNum = currentNum + 1
    else
       currentNum = nil
    end

    return currentNum

end

for i in singleIterator do
    print("i=" .. i)
end
