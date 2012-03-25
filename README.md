- CSRF対策用トークンオブジェクト

 - セキュアな乱数生成器によるトークンの生成
 - トークンをセッションに格納
 - トークンの検証

```scala
// トークンの生成とセッションへの格納
val token = CsrfToken().generate(httpRequest)
// トークンの検証
CsrfToken().validate(httpRequest, token)
```
