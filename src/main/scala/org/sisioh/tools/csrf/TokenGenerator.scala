package org.sisioh.tools.csrf

import org.apache.commons.codec.binary.Hex
import java.security.SecureRandom

/**
 * トークン生成器。
 *
 * @author junichi_kato
 */
trait TokenGenerator {
  /**
   * トークンを生成する。
   *
   * @return トークンを表す文字列
   */
  def generate: String
}

/**
 * コンパニオンオブジェクト。
 *
 * @author junichi_kato
 */
object TokenGenerator {
  /**
   * ファクトリメソッド。
   *
   * @return [[org.sisioh.tools.csrf.TokenGenerator]]
   */
  def apply(): TokenGenerator =
    new DefaultTokenGenerator
}

/**
 * [[org.sisioh.tools.csrf.TokenGenerator]]のデフォルト実装。
 *
 * @author junichi_kato
 */
class DefaultTokenGenerator
  extends TokenGenerator {

  private val secureRandom = new SecureRandom
  private val TOKEN_BYTES_LENGTH = 20
  // 初期化時に強制的にシードする
  secureRandom.nextBytes(Array.fill[Byte](1)(0))


  def generate = {
    val bytes = new Array[Byte](TOKEN_BYTES_LENGTH);
    secureRandom.nextBytes(bytes);
    new String(Hex.encodeHex(bytes));
  }
}



