- CSRF対策用トークンオブジェクト

 - セキュアな乱数生成器によるトークンの生成
 - トークンをセッションに格納
 - トークンの検証

```scala
val csrfToken = CsrfToken("hoge.fuga.token")
// トークンの生成とセッションへの格納
val token = csrfToken.generate(httpRequest)
// トークンの検証
csrfToken.validate(httpRequest, token)
```
