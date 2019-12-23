package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 *
 * SecurityConfig implementation
 * BasicAuth - off. Need to test and switch on
 *
 * @author Konkin Anton
 * @date 23.12.2019
 */

@RestController
public class CommentControllerImpl implements CommentController{

    @ApiOperation(value = "Get last N {num} comments", response = FullCommentDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Server error messages"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("comments/{num}")
    @Override
    public FullCommentDTO getLatestComments(@PathVariable("num") Integer num) {
        return null;
    }

    @ApiOperation(value = "Post comments to selected car with specific serial_number", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200"),
            @ApiResponse(code = 400, message = "Server error messages"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @PostMapping("comment?serial_number=sn")
    @Override
    public void postComment(@RequestParam(name = "sn") String serialNumber, AddCommentDTO comment) {

    }
}
