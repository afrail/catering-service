package com.catering.service.auth.controller;

import com.catering.service.auth.model.UserInfo;
import com.catering.service.auth.service.UserInfoService;
import com.catering.service.auth.utill.AuthTokenUtils;
import com.catering.service.response.ApiConstants;
import com.catering.service.response.CommonResponse;
import com.catering.service.response.MessageConstants;
import com.catering.service.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.catering.service.response.ApiConstants.*;
import static com.catering.service.response.MessageConstants.*;

/**
 * @version 1.0.0
 * @Project catering-service
 * @Author Afrail Hossain
 * @Since 27 January 2025
 */

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.SYSTEM_ADMIN_END_USER+"user")
public class UserRegistrationController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);

    private final UserInfoService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final AuthTokenUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody UserInfo body, HttpServletRequest request){
        try {
            body.setEntryUserId(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody UserInfo body, HttpServletRequest request){
        try {
            body.setUpdateUserId(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody UserInfo body){
        try {
            return commonUtils.generateSuccessResponse(service.delete(body), DELETE_MESSAGE, DELETE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping
    public CommonResponse getAll(){
        try {
            return commonUtils.generateSuccessResponse(service.getAll());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping( value = ACTIVE_PATH, produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getAllActive(){
        try {
            return commonUtils.generateSuccessResponse(service.getAllActive());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping( value = PAGEABLE_PATH, produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPagableList(@PathVariable(PAGEABLE_PAGE) int page, @PathVariable(PAGEABLE_SIZE)int size){
        try {
            return commonUtils.generateSuccessResponse(service.getPageableList(page,size));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
}
