#!/usr/local/bin/lua
local cron = require("cron")

local function printMsg()
  local msg = "hello cron"
  print(msg .. "\n")
end

local lock = cron.every(5, printMsg)

print "=== END ==="
