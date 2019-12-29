package com.telran.ilcarro.controller;

import com.telran.ilcarro.model.web.comment.AddCommentDTO;
import com.telran.ilcarro.model.web.comment.FullCommentDTO;
import com.telran.ilcarro.service.CommentService;
import com.telran.ilcarro.service.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SecurityConfig implementation
 * BasicAuth - off. Need to test and switch on
 *
 * @author Konkin Anton
 * @date 23.12.2019
 */

@RestController
public class CommentControllerImpl implements CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    TokenService tokenService;

    //=============================================================================
    @ApiOperation(value = "Get last 3 comments", response = FullCommentDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Server error messages"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @GetMapping("comments")
    @Override
    public List<FullCommentDTO> getLatestComments() {
        return commentService.getLatestComments(3);
    }

    //=============================================================================
    @ApiOperation(value = "Post comments to selected car with specific serial_number", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200"),
            @ApiResponse(code = 400, message = "Server error messages"),
            @ApiResponse(code = 404, message = "Not Found")
    }
    )
    @PostMapping("comment")
    @Override
    public void postComment(@RequestParam(name = "serial_number") String serialNumber,
                            @RequestHeader("Authorization") String token,
                            @RequestBody AddCommentDTO comment) {
        String ownerEmail = tokenService.decodeToken(token).email;
        commentService.postComment(serialNumber, ownerEmail, comment);
    }
}
