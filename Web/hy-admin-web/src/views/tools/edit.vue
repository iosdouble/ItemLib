<style rel="stylesheet/scss" lang="scss" scoped>
.container{
  padding: 20px;
  position: relative;
  width: 100%;
  height: calc(100vh - 84px);
}
</style>

<template>
  <div class = "container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户">
        <el-input v-model="form.users" type="textarea" placeholder="请输入域账号,用英文逗号隔开" style="width:500px" />
      </el-form-item>
      <el-form-item label="提示">
        <el-input v-model="form.reminder" placeholder="请输入提示" />
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="form.title" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="副标题">
        <el-input v-model="form.subTitle" placeholder="请输入副标题" />
      </el-form-item>
      <el-form-item label="底部标题">
        <el-input v-model="form.bottomTitle" placeholder="请输入底部标题" />
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="form.content" type="textarea" :autosize="{ minRows: 12 }" placeholder="请输入内容" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">发送</el-button>
        <el-button @click="onClear">清空</el-button>
      </el-form-item>
  </el-form>
  </div>
</template>
<script>
import { sendMessageToMeiban } from '@/api/tools'
export default {
  data() {
    return {
      form: {
        users: '',
        reminder: '',
        title: '',
        subTitle: '',
        bottomTitle: '',
        content: ''
      }
    }
  },
  mounted() {
    this.form.reminder = '国美技术中台故障通报'
  },
  methods: {
    onSubmit() {
      const requestData = {}
      requestData.reminder = this.form.reminder
      requestData.users = this.form.users.split(',')
      requestData.title = this.form.title
      requestData.subTitle = this.form.subTitle
      requestData.bottomTitle = this.form.bottomTitle
      requestData.content = this.form.content
      // console.log(requestData)
      sendMessageToMeiban(requestData).then(response => {
        console.log(response)
        this.$message({
          type: 'success',
          message: '发送成功'
        })
      }).catch(err => {
        console.log(err)
        this.$message({
          type: 'error',
          message: '发送失败'
        })
      })
      console.log('submit!')
    },
    onClear() {
      this.form = {}
    }
  }
}
</script>
