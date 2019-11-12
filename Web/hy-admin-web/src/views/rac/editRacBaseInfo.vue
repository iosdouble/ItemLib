<style rel="stylesheet/scss" lang="scss" scoped xmlns:el-col="http://www.w3.org/1999/html">

  .container {
    margin: 5px;
    padding: 32px;
    background-color: rgba(0, 0, 0, 0);
    position: relative;
    width: 100%;
    height: 100%;
  }

  /*给所有的select标签都加上父控件宽度*/
  .el-select-width {
    width: 100%;
  }

  label {
    font-size: 15px;
  }

  .tag-title-font-size {
    font-size: 15px;
  }
</style>


<template>
  <div class="container">

    <el-tabs v-model="activeName">
      <el-tab-pane label="基本信息" name="1">
        <el-row>
           <span>
              <el-tag class="tag-title-font-size">主题描述</el-tag>
             <!--              <label>主题描述</label>-->
              <el-input label="主题描述" v-model="ruleFormEdit.title"/>
           </span>
        </el-row>


        <el-row>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">故障编号</el-tag>
            <!--            <label>故障编号</label>-->
            <el-input v-model="ruleFormEdit.faultId"/>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">系统归属</el-tag>
            <!--            <label>系统归属</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.belongSys" placeholder="系统归属">
              <el-option label="自研" value="ziyan"/>
              <el-option label="厂商" value="changshang"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">RAC系统归属</el-tag>
            <!--            <label>RAC逻辑层</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.raclogicLayer" placeholder="请选择RAC逻辑层">
              <el-option label="应用服务" value="shanghai"/>
              <el-option label="中间件服务" value="beijing"/>
              <el-option label="数据库服务" value="beijing"/>
              <el-option label="系统服务(硬件和操作系统)" value="beijing"/>
              <el-option label="网络服务" value="beijing"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">关联ID</el-tag>
            <!--            <label>关联id</label>-->
            <el-input v-model="ruleFormEdit.relationId"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">故障等级</el-tag>
            <!--            <label>故障等级</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.faultLayer" placeholder="请选择故障等级">
              <el-option label="A" value="shanghai"/>
              <el-option label="B" value="beijing"/>
              <el-option label="C" value="beijing"/>
              <el-option label="D" value="beijing"/>
              <el-option label="E" value="beijing"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">是否整改</el-tag>
            <!--            <label>是否整改</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.isChange" placeholder="是否整改完成">
              <el-option label="未完成" value="ziyan"/>
              <el-option label="已完成" value="changshang"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">是否处理</el-tag>
            <!--            <label>是否处理</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.isDeal" placeholder="处理人是否处理">
              <el-option label="是" value="yes"/>
              <el-option label="否" value="no"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-tag class="tag-title-font-size">处理人邮箱</el-tag>
            <!--            <label>处理人邮箱</label>-->
            <el-input class="el-select-width" v-model="ruleFormEdit.dealEmail"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-tag class="tag-title-font-size">部门</el-tag>
            <!--            <label>部门</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.department" placeholder="请选择部门">
              <el-option label="A" value="shanghai"/>
              <el-option label="B" value="beijing"/>
              <el-option label="C" value="beijing"/>
              <el-option label="D" value="beijing"/>
              <el-option label="E" value="beijing"/>
            </el-select>
          </el-col>
          <el-col :span="8">
            <el-tag class="tag-title-font-size">业务</el-tag>
            <!--            <label>业务</label>-->
            <el-select class="el-select-width" v-model="ruleFormEdit.service" placeholder="请选择业务">
              <el-option label="未完成" value="ziyan"/>
              <el-option label="已完成" value="changshang"/>
            </el-select>
          </el-col>
          <el-col :span="8">
            <el-tag class="tag-title-font-size">涉及部门</el-tag>
            <!--            <label>涉及部门</label>-->
            <el-input class="el-select-width" v-model="ruleFormEdit.relDepartment"/>
          </el-col>
        </el-row>


        <el-row>
          <el-col :span="12">
            <el-col :span="4">
              <el-tag class="tag-title-font-size">开始时间</el-tag>
              <!--              <label>开始时间</label>-->
            </el-col>
            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleFormEdit.startTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2" style="text-align: center">-</el-col>
            <el-col :span="9">
              <el-time-picker placeholder="选择时间" v-model="ruleFormEdit.startTime" style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>

          <el-col :span="12">
            <el-col :span="4">
              <el-tag class="tag-title-font-size">定位时间</el-tag>
              <!--              <label>定位时间</label>-->
            </el-col>
            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleFormEdit.fixedTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2" style="text-align: center">-</el-col>
            <el-col :span="9">
              <el-time-picker placeholder="选择时间" v-model="ruleFormEdit.fixedTime"
                              style="width: 100%;"></el-time-picker>
            </el-col>

          </el-col>

        </el-row>


        <el-row>
          <el-col :span="12">
            <el-col :span="4">
              <el-tag class="tag-title-font-size">结束时间</el-tag>
              <!--              <label>结束时间</label>-->
            </el-col>

            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleFormEdit.endTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2" style="text-align: center">-</el-col>
            <el-col :span="9">
              <el-time-picker placeholder="选择时间" v-model="ruleFormEdit.endTime"
                              style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>


          <el-col :span="12">
            <el-col :span="4">
              <el-tag class="tag-title-font-size">改进时间</el-tag>
              <!--              <label>改进时间</label>-->
            </el-col>

            <el-col :span="9">
              <el-date-picker type="date" placeholder="选择日期" v-model="ruleFormEdit.updateTime"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2" style="text-align: center">-</el-col>
            <el-col :span="9">
              <el-time-picker placeholder="选择时间" v-model="ruleFormEdit.updateTime"
                              style="width: 100%;"></el-time-picker>
            </el-col>
          </el-col>
        </el-row>


        <el-button type="primary" @click="nextTag" style="float: right">填写详细信息</el-button>


      </el-tab-pane>
      <el-tab-pane label="详细信息" name="2">

        <!--          设置详细信息页面-->
        <el-row>
          <el-col :span="6">
            <label>反馈次数</label>
            <el-input v-model="ruleFormEdit.feedbackCount"/>
          </el-col>
          <el-col :span="6">
            <label>是否影响体验</label>
            <el-select v-model="ruleFormEdit.isExperience" placeholder="是否影响体验">
              <el-option label="影响" value="0"/>
              <el-option label="不影响" value="1"/>
            </el-select>
          </el-col>
          <el-col :span="6">
            <label>整改负责人</label>
            <el-input v-model="ruleFormEdit.personLiable"/>

          </el-col>
          <el-col :span="6">
            <label>完成情况</label>
            <el-select v-model="ruleFormEdit.isFinishDes" placeholder="请选择完成情况">
              <el-option label="未完成" value="0"/>
              <el-option label="已完成" value="1"/>
            </el-select>
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
        <label>发现途径与描述</label>
        <el-input v-model="ruleFormEdit.discoveryWayandDes" type="textarea"/>
        <el-row>
          <label>解决方案改进</label>
          <el-button style="float: right" type="text" @click="handleCreate">新增改进方案</el-button>
<!--          <el-input v-model="ruleFormEdit.solutionWayandDes" type="textarea"/>-->
        </el-row>

        <el-row style="height: auto">
          <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column
              prop="title"
              label="改进内容">
            </el-table-column>
            <el-table-column
              prop="name"
              label="责任人"
              width="180">
            </el-table-column>
            <el-table-column
              prop="time"
              width="180"
              label="计划完成时间">
            </el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" type="primary">完成</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>


        <label>处理人反馈结果</label>
        <el-input v-model="ruleFormEdit.dealPersonFback" type="textarea"/>
        <label>根本原因</label>
        <el-input v-model="ruleFormEdit.rootReason" type="textarea"/>


        <el-button type="primary" @click="submitFormEdit">立即创建</el-button>
        <el-button @click="resetFormEdit">重置</el-button>
      </el-tab-pane>
      <el-tab-pane label="上传RAC报告" name="fourth">
        <el-upload class="upload-demo" ref="upload" action="https://jsonplaceholder.typicode.com/posts/"
                   :on-preview="handlePreview"
                   :on-remove="handleRemove" :file-list="fileList" :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
          <div slot="tip" class="el-upload__tip">请上传对应的文档</div>
        </el-upload>
      </el-tab-pane>
    </el-tabs>

    <el-dialog title="增加整改方案" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="整改内容" :label-width="formLabelWidth">
          <el-input v-model="form.title" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="整改责任人" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item label="改进时间" >
          <el-col :span="6">
            <el-form-item >
              <el-date-picker type="date" placeholder="选择日期" v-model="form.updateTime" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2" style="text-align: center">-</el-col>
          <el-col :span="6">
            <el-form-item >
              <el-time-picker placeholder="选择时间" v-model="form.updateTime" style="width: 100%;"></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>
<script>
  export default {
    data() {
      return {
        dialogFormVisible:false,
        activeName: '1',
        tableData:[{
          title:"这个是个亿测试项",
          name:"倪辉，test",
          time:"2019-11-12",

        }],
        form:{
          title:"",
          name:"",
          updateTime:""
        },
        ruleFormEdit: {
          title: '', //主题描述
          faultId: '', //故障编号
          belongSys: '', //系统归属
          raclogicLayer: '', //rac逻辑层
          relationId: '', //关联ID
          faultLayer: '',//故障等级
          isChange: '',//是否整改
          isDeal: '', //是否处理
          dealEmail: '', //处理人邮箱
          department: '', //部门
          service: '', // 业务
          relDepartment: '', // 涉及部门
          startTime: '',
          fixedTime: '', //定位时间
          endTime: '',
          updateTime: '', //改进时间
          isExperience: '', //是否影响体验
          personLiable: '', //改进责任人
          feedbackCount: '', //反馈次数
          isFinishDes: '', // 完成情况

          discoveryWayandDes: '',//发现途径及描述
          solutionWayandDes: '', //解决方案及描述
          dealPersonFback: '', // 处理人反馈结果
          rootReason: '' //根本原因
        }
      }
    },
    methods: {
      submitFormEdit() {
        alert('功能待开发中')
      },
      resetFormEdit(formName) {
        alert('功能未实现')
      },
      nextTag() {
        this.activeName = '2'
      },
      handleCreate() {
        this.dialogFormVisible = true
      },
      addChangeSolution(){

      }
    }
  }
</script>
