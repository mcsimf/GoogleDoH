package com.mcsimf.doh.core.googledoh

import com.mcsimf.doh.core.googledoh.entity.DoHResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Maksym Fedyay on 11/26/20 (mcsimf@gmail.com).
 */
interface DoH {

    @GET("/resolve?")
    /**
     * Resolves DNS by looking up in Google Public DNS servers.
     *
     * @param name The only required parameter. RFC 4343 backslash escapes are accepted.
     * The length (after replacing backslash escapes) must be between 1 and 253 (ignoring an optional trailing dot if present).
     * All labels (parts of the name betweendots) must be 1 to 63 bytes long.
     * Invalid names like .example.com, example..com or empty string get 400 Bad Request.
     * Non-ASCII characters should be punycoded (xn--qxam, not ελ).
     *
     * @param type default: 1
     * RR type can be represented as a number in [1, 65535] or a canonical string
     * (case-insensitive, such as A or aaaa). You can use 255 for 'ANY' queries
     * but be aware that this is not a replacement for sending queries for both
     * A and AAAA or MX records. Authoritative name servers need not return all
     * records for such queries; some do not respond, and others (such as cloudflare.com)
     * return only HINFO.
     *
     * @param cd default: false
     * The CD (Checking Disabled) flag. Use cd=1, or cd=true to disable DNSSEC validation;
     * use cd=0, cd=false, or no cd parameter to enable DNSSEC validation.
     *
     * @param ct default: empty
     * Desired content type option. Use ct=application/dns-message to receive a binary
     * DNS message in the response HTTP body instead of JSON text.
     * Use ct=application/x-javascript to explicitly request JSON text.
     * Other content type values are ignored and default JSON content is returned.
     *
     * @param do default: false
     * The DO (DNSSEC OK) flag. Use do=1, or do=true to include DNSSEC records
     * (RRSIG, NSEC, NSEC3); use do=0, do=false, or no do parameter to omit DNSSEC records.
     * Applications should always handle (and ignore, if necessary) any DNSSEC
     * records in JSON responses as other implementations may always include them,
     * and we may change the default behavior for JSON responses in the future.
     * (Binary DNS message responses always respect the value of the DO flag.)
     *
     * @param random_padding string, ignored
     * The value of this parameter is ignored. Example: XmkMw~o_mgP2pf.gpw-Oi5dK.
     * API clients concerned about possible side-channel privacy attacks using the
     * packet sizes of HTTPS GET requests can use this to make all requests exactly
     * the same size by padding requests with random data.
     * To prevent misinterpretation of the URL, restrict the padding characters to the
     * unreserved URL characters: upper- and lower-case letters, digits, hyphen, period,
     * underscore and tilde.
     */
    suspend fun lookupDoH(
        @Query("name") name: String,
        @Query("type") type: Short = 1,
        @Query("cd") cd: Boolean = false,
        @Query("ct") ct: String = "",
        @Query("do") _do: Boolean = false,
//        @Query("edns_client_subnet") ednsClientSubnet: String = "",
        @Query("random_padding") randomPadding: String = ""
    ): DoHResponse

}