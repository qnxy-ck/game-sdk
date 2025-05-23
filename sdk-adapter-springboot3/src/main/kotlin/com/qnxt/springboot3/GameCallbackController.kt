package com.qnxt.springboot3


import com.qnxy.GameCallbackProcessor
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Qnxy
 */
@RestController
@RequestMapping("\${game.config.callback-uri:/callback}")
public class GameCallbackController(
    private val gameCallbackProcessor: GameCallbackProcessor
) {


    @PostMapping("/{path}")
    public fun callback(@RequestHeader header: HttpHeaders, @RequestBody json: String, @PathVariable path: String): ResponseEntity<String> {
        
        val r = gameCallbackProcessor.handler(header.asSingleValueMap(), json, path)
        return ResponseEntity.status(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r)
    }

}