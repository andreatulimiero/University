object A2 {
    def trim(s:String, p:Char => Boolean) = {
        s.dropWhile(p).reverse.dropWhile(p).reverse
    }
}
