<style rel="stylesheet/scss" lang="scss" scoped xmlns:el-col="http://www.w3.org/1999/html">
  .container {
    padding: 32px;
    background-color: rgba(0, 0, 0, 0);
    position: relative;
    width: 100%;
    height: 100%;

    p {
      color: red;
    }

    .description {
      height: 200px;
    }
  }
</style>

<template>
  <div class="container">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="110px" class="demo-ruleForm" :label-position="left">
      <el-form-item label="主题描述" prop="name">
        <el-input v-model="ruleForm.title"/>
      </el-form-item>
      <!--      <el-form-item label="故障编号" prop="name">-->
      <!--        <el-input v-model="ruleForm.name"></el-input>-->
      <!--      </el-form-item>-->

      <el-row>
        <el-col :span="6">
          <el-form-item label="故障编号">
            <el-input v-model="ruleForm.faultId"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="系统归属">
            <el-select v-model="ruleForm.belongSys" placeholder="系统归属">
              <el-option label="自研" value="ziyan"/>
              <el-option label="厂商" value="changshang"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="RAC逻辑层" >
            <el-select v-model="ruleForm.raclogicLayer" placeholder="请选择RAC逻辑层">
              <el-option label="应用服务" value="shanghai"/>
              <el-option label="中间件服务" value="beijing"/>
              <el-option label="数据库服务" value="beijing"/>
              <el-option label="系统服务(硬件和操作系统)" value="beijing"/>
              <el-option label="网络服务" value="beijing"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="关联ID" >
            <el-input v-model="ruleForm.relationId"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6">
          <el-form-item label="故障等级" >
            <el-select v-model="ruleForm.faultLayer" placeholder="请选择故障等级">
              <el-option label="A" value="shanghai"/>
              <el-option label="B" value="beijing"/>
              <el-option label="C" value="beijing"/>
              <el-option label="D" value="beijing"/>
              <el-option label="E" value="beijing"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="是否整改" >
            <el-select v-model="ruleForm.isChange" placeholder="是否整改完成">
              <el-option label="未完成" value="ziyan"/>
              <el-option label="已完成" value="changshang"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="是否处理" >
            <el-select v-model="ruleForm.isDeal" placeholder="处理人是否处理">
              <el-option label="是" value="yes"/>
              <el-option label="否" value="no"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="处理人邮箱" >
            <el-input v-model="ruleForm.dealEmail"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="6">
          <el-form-item label="部门" >
            <el-select v-model="ruleForm.department" placeholder="请选择部门">
              <el-option label="A" value="shanghai"/>
              <el-option label="B" value="beijing"/>
              <el-option label="C" value="beijing"/>
              <el-option label="D" value="beijing"/>
              <el-option label="E" value="beijing"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="业务" >
            <el-select v-model="ruleForm.service" placeholder="请选择业务">
              <el-option label="未完成" value="ziyan"/>
              <el-option label="已完成" value="changshang"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="涉及部门" >
            <el-input v-model="ruleForm.relDepartment"/>
          </el-form-item>
        </el-col>
      </el-row>


      <el-row>
        <el-col :span="8">
<!--          <el-form-item label="开始时间" required>-->
          <el-form-item label="开始时间" >
            <el-col :span="11">
              <el-form-item >
                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.startTime" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item >
                <el-time-picker placeholder="选择时间" v-model="ruleForm.startTime" style="width: 100%;"></el-time-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>

        <el-col :span="8">

            <el-form-item label="定位时间" >
              <el-col :span="11">
                <el-form-item >
                  <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.fixedTime" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col class="line" :span="2">-</el-col>
              <el-col :span="11">
                <el-form-item >
                  <el-time-picker placeholder="选择时间" v-model="ruleForm.fixedTime" style="width: 100%;"></el-time-picker>
                </el-form-item>
              </el-col>
            </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="结束时间" >
            <el-col :span="11">
              <el-form-item >
                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.endTime" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item >
                <el-time-picker placeholder="选择时间" v-model="ruleForm.endTime" style="width: 100%;"></el-time-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="改进时间" >
            <el-col :span="11">
              <el-form-item >
                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.updateTime" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item >
                <el-time-picker placeholder="选择时间" v-model="ruleForm.updateTime" style="width: 100%;"></el-time-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
        </el-col>
      </el-row>





      <el-row>
        <el-col :span="6">
          <el-form-item label="反馈次数" >
            <el-input v-model="ruleForm.feedbackCount"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="影响体验" >
            <el-select v-model="ruleForm.isExperience" placeholder="是否影响体验">
              <el-option label="影响" value="0"/>
              <el-option label="不影响" value="1"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="整改负责人">
            <el-input v-model="ruleForm.personLiable"/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="完成情况">
            <el-select v-model="ruleForm.isFinishDes" placeholder="请选择完成情况">
              <el-option label="未完成" value="0"/>
              <el-option label="已完成" value="1"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!--      <el-form-item label="即时配送" prop="delivery">-->
      <!--        <el-switch v-model="ruleForm.delivery" />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="活动性质" prop="type">-->
      <!--        <el-checkbox-group v-model="ruleForm.type">-->
      <!--          <el-checkbox label="美食/餐厅线上活动" name="type" />-->
      <!--          <el-checkbox label="地推活动" name="type" />-->
      <!--          <el-checkbox label="线下主题活动" name="type" />-->
      <!--          <el-checkbox label="单纯品牌曝光" name="type" />-->
      <!--        </el-checkbox-group>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="特殊资源" prop="resource">-->
      <!--        <el-radio-group v-model="ruleForm.resource">-->
      <!--          <el-radio label="线上品牌商赞助" />-->
      <!--          <el-radio label="线下场地免费" />-->
      <!--        </el-radio-group>-->
      <!--      </el-form-item>-->
      <el-form-item label="发现途径与描述" >
        <el-input v-model="ruleForm.discoveryWayandDes" type="textarea"/>
      </el-form-item>
      <el-form-item label="解决方案与改进" >
        <el-input v-model="ruleForm.solutionWayandDes" type="textarea"/>
      </el-form-item>
      <el-form-item label="处理人反馈结果" >
        <el-input v-model="ruleForm.dealPersonFback" type="textarea"/>
      </el-form-item>
      <el-form-item label="根本原因" >
        <el-input v-model="ruleForm.rootReason" type="textarea"/>
      </el-form-item>
      <el-form-item label="上传报告">
        <el-upload class="upload-demo" ref="upload" action="https://jsonplaceholder.typicode.com/posts/"
                   :on-preview="handlePreview"
                   :on-remove="handleRemove" :file-list="fileList" :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
          <div slot="tip" class="el-upload__tip">请上传对应的文档</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          title:'', //主题描述
          faultId:'', //故障编号
          belongSys:'', //系统归属
          raclogicLayer:'', //rac逻辑层
          relationId:'', //关联ID
          faultLayer:'',//故障等级
          isChange:'',//是否整改
          isDeal:'', //是否处理
          dealEmail:'', //处理人邮箱
          department:'', //部门
          service:'', // 业务
          relDepartment:'', // 涉及部门
          startTime:'',
          fixedTime:'', //定位时间
          endTime:'',
          updateTime:'', //改进时间
          isExperience:'', //是否影响体验
          personLiable:'', //改进责任人
          feedbackCount:'', //反馈次数
          isFinishDes:'', // 完成情况

          discoveryWayandDes:'',//发现途径及描述
          solutionWayandDes:'', //解决方案及描述
          dealPersonFback:'', // 处理人反馈结果
          rootReason:'', //根本原因
        },
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!')
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      }
    }
  }
</script>

<style>
</style>
