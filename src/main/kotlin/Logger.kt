import java.util.logging.Logger

private val log = Logger.getLogger("Logs")
internal fun i(tag: String, msg: String) {
    log.info("[$tag] - $msg")
}