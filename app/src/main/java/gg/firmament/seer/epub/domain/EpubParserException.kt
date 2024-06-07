package gg.firmament.seer.epub.domain

/**
 * Exception thrown when an error occurs while parsing an EPUB file.
 *
 * @param message The error message.
 */
class EpubParserException(message: String) : Exception(message)
