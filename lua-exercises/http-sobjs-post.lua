local http = require("socket.http")
local ltn12 = require("ltn12")

local respbody = {}

local host = host or "localhost"
local port = port or 9009

local function getTableSize(x)
  local ret = 0
  if(x == nil) then
    return ret
  end

  for lk,lv in pairs(x) do
    if lk ~= nil then
      ret = ret +1
    else
      print("nil key found")
    end
  end

  return ret
end

if arg then
  host = arg[1] or host
  port = arg[2] or port
end

print("connect to " .. host .. ":" .. port)
local surl = "http://" .. host .. ":" .. port .. "/cfbook/sobjs"
local smethod = "POST"
math.randomseed(tostring(os.time()):reverse():sub(1, 6))
local randomNumber = math.random(1000,9999)
local time = os.time()
local content = "lua-test-" .. time .. randomNumber

local reqbody = string.format("{\"content\":\"%s\"}",content)
print("reqbody:" .. reqbody)

local  body, code, headers, status = http.request{
  method=smethod,
  url=surl,
  source = ltn12.source.string(reqbody),
  headers = {
    ["Accept"] = "*/*",
    ["Accept-Encoding"] = "gzip, deflate",
    ["Accept-Language"] = "en-us",
    ["Content-Type"] = "application/json",
    ["content-length"] = string.len(reqbody)
  },
  sink = ltn12.sink.table(respbody)
}

print("code:" .. code)
if respbody ~= nil and getTableSize(respbody) > 0 then
  print("respbody:")
  for bk,bv in pairs(respbody) do
    print(bv)
  end
end

--[[
crontab -e
*/1 * * * * /usr/local/bin/lua /home/gavin/Dev/GitRepo/dwss/lua-exercises/http-sobjs-post.lua >> /home/gavin/Dev/GitRepo/dwss/lua-exercises/lua-post.out 2>&1
]]--
