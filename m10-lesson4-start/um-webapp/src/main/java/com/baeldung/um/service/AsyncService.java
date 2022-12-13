package com.baeldung.um.service;

import com.baeldung.um.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {

    public static final long DELAY = 10000L;

    @Autowired
    private IUserService userService;

    public void scheduleCreateUser(UserDto resource, DeferredResult<UserDto> deferredResult) {
        CompletableFuture.supplyAsync(()-> userService.createSlow(resource))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result) );
    }

    @Async
    public Future<UserDto> createUserAsync(UserDto resource) throws InterruptedException {
        resource.setStatus("In Progress");

        UserDto result = userService.create(resource);
        Thread.sleep(AsyncService.DELAY);

        result.setStatus("Ready");

        userService.update(result);

        return new AsyncResult<UserDto>(result);
    }
}
