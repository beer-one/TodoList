<template>
  <section class="todolist">
    <ul class="table">
      <li v-for="(todoItem, index) in propsdata" class="shadow" v-bind:key="todoItem">

        <span v-bind:class="[{'state-completed': todoItem.finished}, {'state-incompleted': todoItem.ended}, {'state-proceeding': todoItem.proceeding}, {'state-nothing': !todoItem.ended && !todoItem.proceeding}]">
          <i class="fa fa-solid fa-circle"></i>
        </span>
        <span class="title">{{ todoItem.title }}</span>
        <span class="datetime">{{ todoItem.startDateTime }}</span>
        <span class="datetime">{{ todoItem.endDateTime }}</span>
        <span class="removeBtn" type="button" @click="removeTodo(todoItem, index)">
          <i class="fa fa-trash-o" aria-hidden="true"></i>
        </span>

      </li>
    </ul>
  </section>
</template>

<script>
export default {
  props: ['propsdata'],
  methods: {
    removeTodo (todoItem, index) {
      this.$emit('removeTodo', todoItem, index)
    }
  }
}
</script>

<style>

  .todolist {
    margin-top: 30px;
    margin-bottom: 20px;
    margin-left: 5%;
    margin-right: 5%;
    background-color: #93bbff;
    padding: 5px 10px;
    height: auto;
  }

  ul {
    list-stype-type: none;
    padding-left: 0px;
    margin-top: 0;
    text-align: left;
  }

  li {
    display: flex;
    justify-content: flex-start;
    min-height: 50px;
    height: 50px;
    line-height: 50px;
    margin: 0.5rem 0;
    padding: 0 0.9rem;
    background: white;
    border-radius: 5px;
  }

  .datetime {
    flex: 2 2 2;
    padding-left: 10px;
  }

  .title {
    flex: 9 9 auto;
  }

  .removeBtn {
    flex: none;
    margin-left: auto;
    padding-left: 10px;
    color: #de4343
  }

  .state-nothing {
    padding-right: 10px;
    visibility: hidden;
  }

  .state-completed {
    padding-right: 10px;
    color: #00CC00
  }

  .state-incompleted {
    padding-right: 10px;
    color: #FF3333
  }

  .state-proceeding {
    padding-right: 10px;
    color: #FFDD00
  }


</style>
