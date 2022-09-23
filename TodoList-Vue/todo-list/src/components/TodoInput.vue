<template>
  <div class="inputBox shadow">


    <input type="text" class="input-item" v-model="title" placeholder = "제목" v-on:keyup.enter="addTodo">
    <input type="text" class="input-item" v-model="content" placeholder = "내용" v-on:keyup.enter="addTodo">
    <Datepicker class="datepicker input-item" v-model="startDateTime" placeholder = "시작 시간" :enableTimePicker="true"></Datepicker>
    <Datepicker class="datepicker input-item" v-model="endDateTime" placeholder = "종료 시간" :enableTimePicker="true"></Datepicker>
    <span class="addContainer" v-on:click="addTodo">
      <i class="addBtn fa fa-pencil" aria-hidden="true"></i>
    </span>

  </div>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import DateFormatter from '@/utils/DateFormatter.js'

export default {
  components: { Datepicker },

  data () {
    return {
      title: '',
      content: '',
      startDateTime: null,
      endDateTime: null
    }
  },

  methods: {
    addTodo () {
      if (this.newTodoItem !== '') {
        let todoItem = {
          title: this.title && this.title.trim(),
          content: this.content && this.content.trim(),
          startDateTime: DateFormatter.format(this.startDateTime),
          endDateTime: DateFormatter.format(this.endDateTime)
        };

        this.$emit('addTodo', todoItem);
        this.clearInput();
      }
    },
    clearInput () {
      this.title = '';
      this.content = '';
      this.startDateTime = null;
      this.endDateTime = null;
    }
  }
}
</script>

<style>
  input.focus {
    outline: none;
  }

  .inputBox {
    background: white;
    height: 50px;
    line-height: 50px;
    margin-left: 5%;
    margin-right: 5%;
    display: flex;
    justify-content: flex-start;
  }
  .inputBox input {
    border-style: none;
    font-size: 0.9rem;
    text-align: left;
    padding-left: 15px;
  }
  .addContainer {
    background: #5d63FF;
    width: 3rem;
    border-radius: 0 5px 5px 0;
  }
  .addBtn {
    color: white;
    vertical-align: middle;
  }
  .datepicker {
    border-radius: 0 5px 5px 0;
  }
  .input-item:nth-child(1) { flex: 1 1 1; }
  .input-item:nth-child(2) { flex: 9 9 auto; }
  .input-item:nth-child(3) { flex: 2 2 2; }
  .input-item:nth-child(4) { flex: 2 2 2; }



</style>
