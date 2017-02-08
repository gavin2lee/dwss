#!/usr/local/bin/lua
local socket = require("socket")
host = host or "localhost"
port = port or 9009

if arg then
  host = arg[1] or host
  port = arg[2] or port
end

print("Attempting connection to host '" ..host.. "' and port " ..port.. "...")
c = assert(socket.connect(host, port))
print("Connected! Please type stuff (empty line to stop):")

l = io.read()

while l and l ~= "" and not e do
  assert(c:send(l .. "\n"))
  msg = c:receive()

  if not msg then
    print("client exit")
    break
  end
  
  print("FROM SERVER: " .. msg)
  l = io.read()
end
