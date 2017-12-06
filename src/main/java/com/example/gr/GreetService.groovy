package com.example.gr

class GreetService {

    String sayHello() {
        def m = new Message(greeting: "Ciao from Groovy!")
        return m.greeting
    }
}
