
class F{

    test(){
        console.log("F_test")
    }

}


class Test{

    f = new F()

    run(){
        this.f.test()
        this.f.test = ()=>{
            this.testFunc()
        }
        this.f.test()
    }

    testFunc = ()=>{
        this.testFunc2()
    }

    testFunc2 = function (){
        console.log("testFunc2")
    }

}

const x = new Test()
x.run()
x.testFunc = function (){
    console.log("success")
}
x.run()
// x.testFunc2 = ()=>{
//     console.log("123")
// }
// x.testFunc()