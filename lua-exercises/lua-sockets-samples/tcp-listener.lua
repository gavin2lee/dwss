#!/usr/local/bin/lua
--[[
TCP sample
]]--

local socket = require("socket")
host = host or "*"
port = port or "9009"
if arg then
  host = arg[1] or host
  port = arg[2] or port
end

print("binding to host '" .. host .. "' and port " .. port .. "...")
s = assert(socket.bind(host, port))
i,p = s:getsockname()

assert(i,p)
print("Waiting connection from talker on " .. i .. ":" .. p .. "...")
c = assert(s:accept())
cname = assert(c:getpeername())
print("Connected. Here is the stuff - " .. cname)
l,e = c:receive()

while not e do
  print(l)
  if l == "bye" then
    print("server exit right now")
    c:close()
    break
  end
  msg = "hi " .. cname .. " you sent such msg: " .. l
  c:send(msg  .. "\n")
  l,e = c:receive()
end

if e then
  print(e)
end
--file end
