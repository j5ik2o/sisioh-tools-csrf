package org.sisioh.tools.csrf

import javax.servlet.http.HttpServletRequest

/**
 * CSRF対策用のセキュアなトークンを表すモデル。
 *
 * @author junichi_kato
 */
trait CsrfToken {
  /**
   * トークンを取得するもしくは生成します。
   *
   * @param request リクエスト
   * @param create 生成する場合true
   * @return トークンを表す文字列
   */
  def getOrGenerate(request: HttpServletRequest, create: Boolean): String

  /**
   * トークンを取得する。
   *
   * @param request リクエスト
   * @return トークンを表す文字列
   */
  def get(request: HttpServletRequest): String

  /**
   * トークンを生成する。
   *
   * @param request リクエスト
   * @return トークンを表す文字列
   */
  def greate(request: HttpServletRequest): String

  /**
   * トークンを検証する。
   *
   * @param request リクエスト
   * @param token トークンを表す文字列
   * @return 正しいトークンだった場合はtrue
   */
  def validate(request: HttpServletRequest, token: String): Boolean

}

/**
 * コンパニオンオブジェクト。
 *
 * @author junichi_kato
 */
object CsrfToken {
  /**
   * ファクトリメソッド。
   *
   * @return [[org.sisioh.tools.csrf.TokenGenerator]]
   */
  def apply(key: String) = new DefaultCsrfToken(key)
}

/**
 * [[org.sisioh.tools.csrf.CsrfToken]]のデフォルト実装
 *
 * @param key セッションキー
 */
class DefaultCsrfToken(key: String) extends CsrfToken {

  private val TOKEN_SESSION_KEY = key

  def getOrGenerate(request: HttpServletRequest, create: Boolean) = {
    val session = request.getSession
    val tokenObj = session.getAttribute(TOKEN_SESSION_KEY)
    if (create && tokenObj == null) {
      val token = TokenGenerator().generate
      session.setAttribute(TOKEN_SESSION_KEY, token)
      token
    } else {
      tokenObj.asInstanceOf[String]
    }
  }

  def get(request: HttpServletRequest) = getOrGenerate(request, false)

  def greate(request: HttpServletRequest) =
    getOrGenerate(request, true)

  def validate(request: HttpServletRequest, token: String) = {
    if (token == null) {
      false
    } else {
      val tokenObj = request.getSession(true).getAttribute(TOKEN_SESSION_KEY)
      if (tokenObj != null) {
        val tokenMaster = tokenObj.asInstanceOf[String]
        token == tokenMaster
      } else false
    }
  }

}
