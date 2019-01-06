def sum(a:Int,b:Int,c:Int) = a+b+c

def sum2 = (_:Int) + (_:Int)

val a = sum(10,_:Int,_:Int)
a(1,2)

val a1 = (a1:Int,a2:Int)=>sum(10,a1,a2)
a(1,2)