val xs = Array(1, 2, 3, 4, 5)
xs.par.foldLeft(0)(_ + _)