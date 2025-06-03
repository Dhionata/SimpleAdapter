import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Passo 3 - Criar classe para mapear ENDPOINT e configurar classe para fazer o PARSER.
 * Converter JSON para objeto.
 */
object PeopleAPI {
    // No retrofit você precisa colocar o / (slash).
    // Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    // VOLTE PARA HTTPS SE A API SUPORTAR, APÓS CONFIGURAR O OKHTTPCLIENT
    private const val ENDPOINT = "https://swapi.dev/api/" // Idealmente, use HTTPS

    // Crie um TrustManager que não valida cadeias de certificados
    // ATENÇÃO: ISTO É INSEGURO PARA PRODUÇÃO
    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Crie um TrustManager que não valida cadeias de certificados
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                }

                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            // Instale o TrustManager que confia em tudo
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Crie um validador de hostname que não faz nada
            val hostnameVerifier = javax.net.ssl.HostnameVerifier { _, _ -> true }

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(hostnameVerifier) // Desabilita a verificação do hostname
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val client: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(getUnsafeOkHttpClient().build()) // Adicione o OkHttpClient configurado
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}