

### 如何打卡

首先的准备工作是要抓包, 拿到自己的马上办信息数据, 除了cookie外, 其余均是可以复用的, 大概几个必要的抓包参数如下: 

com.dyc12ii.dk.beans.DKSender.Const


然后进入 DKSender中, 在main方法里替换自己的cookie, 运行main方法即可 



### 抓包阅读参考以下打卡的请求顺序

enter.do
    请求地址值: GET /neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1 HTTP/1.1
    获取cookie 和 302:location
    http://numenplus.yixin.im/getWebAuth.do?companyId=1&from=QiyeYixin&appName=netease-attendance&redirectUrl=%2Fneteaseattendance%2Fenter.do%3FappName%3Dnetease-attendance%26from%3DQiyeYixin%26companyId%3D1


getWebAuth.do
    请求地址: GET /getWebAuth.do?companyId=1&from=QiyeYixin&appName=netease-attendance&redirectUrl=%2Fneteaseattendance%2Fenter.do%3FappName%3Dnetease-attendance%26from%3DQiyeYixin%26companyId%3D1 HTTP/1.1
    请求携带cookie
    返回302:location
    https://oauth.qiye.yixin.im/authorize?response_type=code&client_id=9aa20b54841c4d9b8b16c732ee303c91&state=0q9i8y7e6y5i4x3i2n1&redirect_uri=http%3A%2F%2Fnumenplus.yixin.im%2Foauth%2FreturnAuth.do%3FappName%3Dnetease-attendance%26companyId%3D1%26redirectUrl%3D%252Fneteaseattendance%252Fenter.do%253FappName%253Dnetease-attendance%2526from%253DQiyeYixin%2526companyId%253D1
    https://oauth.qiye.yixin.im/authorize?response_type=code&client_id=9aa20b54841c4d9b8b16c732ee303c91&state=0q9i8y7e6y5i4x3i2n1&redirect_uri=http://numenplus.yixin.im/oauth/returnAuth.do?appName=netease-attendance&companyId=1&redirectUrl=%2Fneteaseattendance%2Fenter.do%3FappName%3Dnetease-attendance%26from%3DQiyeYixin%26companyId%3D1

authorize
    请求地址: GET /authorize?st=334ab0dea3b04bf9a519439025db6cf0&response_type=code&client_id=9aa20b54841c4d9b8b16c732ee303c91&state=0q9i8y7e6y5i4x3i2n1&redirect_uri=http://numenplus.yixin.im/oauth/returnAuth.do?appName%3Dnetease-attendance%26companyId%3D1%26redirectUrl%3D%252Fneteaseattendance%252Fenter.do%253FappName%253Dnetease-attendance%2526from%253DQiyeYixin%2526companyId%253D1 HTTP/1.1
    请求不携带cookie
    请求比getWebAuth.do的返回location多了st参数
    返回302:location
    http://numenplus.yixin.im/oauth/returnAuth.do?appName=netease-attendance&companyId=1&redirectUrl=%2Fneteaseattendance%2Fenter.do%3FappName%3Dnetease-attendance%26from%3DQiyeYixin%26companyId%3D1&code=1e9d38e7cb51463a91f5ccac484c91b9&state=0q9i8y7e6y5i4x3i2n1

returnAuth.do
    请求地址: GET /oauth/returnAuth.do?appName=netease-attendance&companyId=1&redirectUrl=%2Fneteaseattendance%2Fenter.do%3FappName%3Dnetease-attendance%26from%3DQiyeYixin%26companyId%3D1&code=1e9d38e7cb51463a91f5ccac484c91b9&state=0q9i8y7e6y5i4x3i2n1 HTTP/1.1
    请求携带cookie
    返回302:location
    http://numenplus.yixin.im/neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1&__UUID__=3F85FB7432091831CF301A65E2805A8D
    http://numenplus.yixin.im/neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1?__UUID__=9ACC356FF78EA6EE35ECB20421037352

enter.do
    请求地址: GET /neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1&__UUID__=3F85FB7432091831CF301A65E2805A8D HTTP/1.1
             GET /neteaseattendance/enter.do?appName=netease-attendance&from=QiyeYixin&companyId=1&__UUID__=194271AA3B90B24FD10D6E0DC4AC94BD HTTP/1.1
    请求携带cookie
    返回页面


### 关于为什么要集成web
该项目可以直接运行Application作为web项目, 其目的是更为简便的打卡, 将web服务部署至公网上, 访问index.html即可打卡
如无公网环境, 可以考虑使用ngrok, 用内网穿透的方式
