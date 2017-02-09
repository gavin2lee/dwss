#!/usr/local/bin/lua
print("http simple object client")
--[[
headers =
               {
                       ["Accept"] = "*/*",
                       ["Accept-Encoding"] = "gzip, deflate",
                       ["Accept-Language"] = "en-us",
                       ["Content-Type"] = "application/x-www-form-urlencoded",
                       ["content-length"] = string.len(reqbody)
               },
]]--

local http = require("socket.http")
local ltn12 = require "ltn12"
--local util = require "util"

local respbody = {}

local host = host or "localhost"
local port = port or 9009

if arg then
  host = arg[1] or host
  port = arg[2] or port
end

print("connect to " .. host .. ":" .. port)
local surl = "http://" .. host .. ":" .. port .. "/cfbook/sobjs"
local smethod = "GET"

local  body, code, headers, status = http.request{
  method=smethod,
  url=surl,
  sink = ltn12.sink.table(respbody)
}

print("body:" .. body)
print("code:" .. code)

print("headers:")
for k,v in pairs(headers) do
  print(k .. ":" .. v)
end

print("status:" .. status)

print("respbody:")
for bk,bv in pairs(respbody) do
  print(bv)
end
