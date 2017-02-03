#!/usr/local/bin/lua
print("table exercises")

weekdays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}

print("type of weekdays:" .. type(weekdays));
print("type of weekdays[0]:" .. type(weekdays[0]))
print("type of weekdays[1]:" .. type(weekdays[1]))

print("====== file operations ========")
require("lfs")

curDir = assert(io.open("./","r"))
print(curDir)

for t in lfs.dir("./") do
    print(t)
end

print "====== Game  =========="
game = require("Game")
game:play()
game:quit()


print "======= ...  ============="
print(...)
