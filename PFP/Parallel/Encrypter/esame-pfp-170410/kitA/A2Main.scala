object A2Main extends App {
    // test 1
    val m1 = A2.trim("  in vino veritas  ", _ == ' ');
    println("\"" + m1 + "\" [corretto: \"in vino veritas\"]")

    // test 2
    val m2 = A2.trim("[This is a test...]", !_.isLetter);
    println("\"" + m2 + "\" [corretto: \"This is a test\"]")
}
