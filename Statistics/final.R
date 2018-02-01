waldInt <- function(s, n) {
  p <- s/n
  z <- qnorm(0.05/2, lower.tail = FALSE)
  standErr <- sqrt((p*(1-p))/n)
  c(p - z*standErr, p + z*standErr)
}

scoreInt <- function(s, n) {
  p <- s/n
  z <- qnorm(0.05/2, lower.tail = FALSE)
  standErr <- (p*(1-p))/n
  c((p + (z*z)/(2*n) - z*(standErr + (z*z)/(4*(n*n))))/(1+(z*z)/n),
    (p + (z*z)/(2*n) + z*(standErr + (z*z)/(4*(n*n))))/(1+(z*z)/n))
}

waldInt(70,100)  #[0.6101832, 0.7898168]
scoreInt(70,100) #[0.6884564, 0.6967462]

binom.test(70,100) #p-value = 7.85e-05

zTest <- function(dataSample) {
  sigma <- sd(dataSample)
  n <- length(dataSample)
  standErr <- sigma/sqrt(n)
  sampMean <- mean(dataSample)
  z <- qnorm(0.05/2, lower.tail = FALSE)
  c(sampMean - z*standErr, sampMean + z*standErr)
}

zTest(chickwts$weight[c(1:10)])  #[136.2599, 184.1401]
zTest(chickwts$weight[c(11:22)]) #[189.1954, 248.3046]
zTest(chickwts$weight[c(23:36)]) #[218.0746, 274.7826]
zTest(chickwts$weight[c(37:48)]) #[301.2854, 356.5479]
zTest(chickwts$weight[c(49:59)]) #[238.5560, 315.2622]
zTest(chickwts$weight[c(60:71)]) #[287.1271, 360.0395]

t.test(chickwts$weight[c(1:10)])  #[132.5687, 187.8313]
t.test(chickwts$weight[c(11:22)]) #[185.561, 251.939]
t.test(chickwts$weight[c(23:36)]) #[215.1754, 277.6818]
t.test(chickwts$weight[c(37:48)]) #[297.8875, 359.9458]
t.test(chickwts$weight[c(49:59)]) #[233.3083, 320.5099]
t.test(chickwts$weight[c(60:71)]) #[282.6440, 364.5226]