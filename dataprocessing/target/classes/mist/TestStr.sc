val line ="""2017-01-05 00:00:07,088 146294779  INFO  [com.tj.arms.sword.DefaultFilter] (default task-7) processed:{"request":{"uid":0,"sucess":false,"dv":2016081001,"linux_v":"Linux version 3.4.67 """

val ymd = line.substring(0, 13).replace(" ","-")
val ts = line.substring(0, 19)
val tails = line.substring(line.indexOf(":{\"request\"") + 2)
val finals = raw""" {"ymd":"$ymd","record_time":"$ts",$tails """
val userID = Map((1 to 100).map(_ -> .01): _*)