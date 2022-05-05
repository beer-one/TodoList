package com.tutorial.todo.application.todo

import com.tutorial.todo.domain.todo.entity.*
import com.tutorial.todo.domain.todo.repository.*
import org.springframework.data.domain.*
import org.springframework.data.repository.*
import org.springframework.stereotype.*
import org.springframework.transaction.support.*
import reactor.core.publisher.*
import java.lang.RuntimeException
import java.time.*

/**
 * @author Seowon Yun
 */
@Service
class TodoListCommandService(
    private val repository: TodoListRepository,
    private val transactionTemplate: TransactionTemplate
) {

    fun add(command: TodoListAddCommand): Mono<Unit> {
        return Mono.fromCallable {
            transactionTemplate.executeWithoutResult {
                repository.save(TodoList.create(command))
            }
        }
    }

    fun edit(no: Int, command: TodoListEditCommand): Mono<Unit> {
        return Mono.fromCallable {
            transactionTemplate.executeWithoutResult {
                repository.findByIdOrNull(no)?.edit(command)
                    ?: throw RuntimeException("$no 번째 TodoList가 없습니다.")
            }
        }
    }

    fun delete(no: Int): Mono<Unit> {
        return Mono.fromCallable {
            transactionTemplate.executeWithoutResult {
                if (repository.existsById(no)) {
                    repository.deleteById(no)
                } else {
                    throw RuntimeException("$no 번째 TodoList가 없습니다.")
                }
            }
        }
    }

}
