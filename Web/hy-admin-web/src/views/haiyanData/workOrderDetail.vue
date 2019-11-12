<style>
.app-container{
  padding: 32px;
  background-color:#FCFCFC;
  position: relative;
  width: 100%;
  height: 100%;
}
  .text {
    font-size: 14px;
  }
  .item {
    margin-bottom: 18px;
    color:#787878;
    font-weight:bolder
  }
  .content {
    margin-left: 40px;
    font-weight: normal
  }
  .clearfix {
    color:#606060;
    font-weight: 900;
    font-size: 50
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
</style>

<template>
  <div class="app-container">
    <div class = "top-container">
      <el-row style="margin-bottom:25px">
        <el-popover
          placement="top"
          width="460"
          v-model="popoverVisible">
          <el-row>
            状态：
            <el-select v-model="submitStatus" filterable placeholder="请选择" @change="submitSelect" >
            <el-option
              v-for="item in submitOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
            </el-select>
          </el-row>
          <el-row style="margin-top:20px">
            备注：
            <el-input v-model="submitContent" type="textarea" placeholder="请输入备注" style="width:200px" />
          </el-row>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="onClear">取消</el-button>
            <el-button type="primary" size="mini" @click="onSubmit">提交</el-button>
          </div>
          <el-button type="success" size="mini" plain :disabled="isSubmit" slot="reference">提交状态<i class="el-icon-upload el-icon--right"></i></el-button>
        </el-popover>
      </el-row>
      <el-row :gutter="25">
        <el-col :span="9">
          <el-card class="box-basic">
            <div slot="header" class="clearfix">
              <span>基本信息</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
            </div>
            <!-- <div v-for="o in 4" :key="o" class="text item">
              {{'列表内容 ' + o }}
            </div> -->
            <div class="text item">
              <div>
                <font color="grey">工单号 :</font><span class="text content">{{ basicInfo.workId }}</span>
              </div>
            </div>
            <div class="text item">
              <font color="grey">申请人 :</font><span class="text content">{{ basicInfo.account }}</span>
            </div>
            <div class="text item">
              <font color="grey">中心职位 :</font><span class="text content">{{ basicInfo.userCn }}</span>
            </div>
            <div class="text item">
              <font color="grey">申请人邮箱 :</font><span class="text content">{{ basicInfo.email }}</span>
            </div>
            <div class="text item">
              <font color="grey">创建日期 :</font><span class="text content">{{ basicInfo.createTime }}</span>
            </div>
            <div class="text item">
              <font color="grey">工单状态 :</font><span class="text content">{{ basicInfo.status }}</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="15">
          <el-card class="box-param">
            <div slot="header" class="clearfix">
              <span>参数信息</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
            </div>
            <div class="text item">
              <div>
                <font color="grey">IP列表 :</font><span class="text content">{{ paramInfo.ipList }}</span>
              </div>
            </div>
            <div class="text item">
              <font color="grey">系统优化参数 :</font><span class="text content">{{ paramInfo.sysArgs }}</span>
            </div>
            <div class="text item">
              <font color="grey">diamond参数 :</font><span class="text content">{{ paramInfo.diamondContent }}</span>
            </div>
            <div class="text item">
              <font color="grey">绑定域名 :</font><span class="text content">{{ paramInfo.domain }}</span>
            </div>
            <div class="text item">
              <font color="grey">CDN地址 :</font><span class="text content">{{ paramInfo.cdnDomain }}</span>
            </div>
            <div class="text item">
              <font color="grey">备注 :</font><span class="text content">{{ paramInfo.note }}</span>
            </div>
          </el-card>
        </el-col>
        </el-row>
        <el-row style="margin-top:25px">
          <el-card class="box-app">
            <div slot="header" class="clearfix">
              <span>应用信息</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
            </div>
            <div class="text item">
              <el-table
                :data="appInfoList"
                style="width: 100%"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}">
                <el-table-column
                  prop="name"
                  label="应用名称"
                >
                </el-table-column>
                <el-table-column
                  prop="port"
                  label="端口"
                >
                </el-table-column>
                <el-table-column
                  prop="mem"
                  label="内存"
                >
                </el-table-column>
                <el-table-column
                  prop="middleware"
                  label="容器"
                >
                </el-table-column>
                <el-table-column
                  prop="jdk"
                  label="JDK"
                >
                </el-table-column>
                <el-table-column
                  prop="sre"
                  label="sre接维"
                >
                </el-table-column>
                <el-table-column
                  prop="note"
                  label="应用描述"
                >
                </el-table-column>
                <el-table-column
                  prop="code"
                  label="应用地址"
                >
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </el-row>
        <el-row style="margin-top:25px">
          <el-card class="box-server">
            <div slot="header" class="clearfix">
              <span>服务器信息</span>
              <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
            </div>

            <div class="text item">
              <el-table
                :data="serverInfoList"
                style="width: 100%"
                :cell-style="cellStyle"
                :header-cell-style="{background:'#eef1f6',color:'#606266'}">
                <el-table-column
                  prop="env"
                  label="环境"
                >
                </el-table-column>
                <el-table-column
                  prop="region"
                  label="机房"
                >
                </el-table-column>
                <el-table-column
                  prop="system"
                  label="系统"
                >
                </el-table-column>
                <el-table-column
                  prop="cpuNum"
                  label="cpu"
                >
                </el-table-column>
                <el-table-column
                  prop="memNum"
                  label="内存"
                >
                </el-table-column>
                <el-table-column
                  prop="diskSize"
                  label="磁盘"
                >
                </el-table-column>
                <el-table-column
                  prop="diskType"
                  label="磁盘类型"
                >
                </el-table-column>
                <el-table-column
                  prop="num"
                  label="数量"
                >
                </el-table-column>
                <el-table-column
                  prop="isNet"
                  label="是否开通外网"
                >
                </el-table-column>
                <el-table-column
                  prop="bastionDomain"
                  label="堡垒机用户"
                >
                </el-table-column>
                <el-table-column
                  prop="status"
                  label="审批状态"
                >
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </el-row>
    </div>
  </div>
</template>

<script>
import { getWorkOrderDetail, workOrderSubmit } from '@/api/haiyanData'
import { parseTime } from '@/utils'
import { isSuccess } from '../../contant'
export default {
  name: 'WorkOrderDetail',
  filters: {
    statusFilter(status) {
      const statusMap = {
        已完成: 'success',
        流程中: 'info',
        未完成: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      popoverVisible: false,
      submitOptions: isSuccess,
      submitContent: '',
      submitStatus: true,
      visible: true,
      basicInfo: {
        workId: '',
        account: '',
        userCn: '',
        email: '',
        createTime: '',
        status: ''
      },
      paramInfo: {
        ipList: '',
        sysArgs: '',
        diamondContent: '',
        domain: '',
        cdnDomain: '',
        note: ''
      },
      appInfoList: [],
      serverInfoList: [],
      isSubmit: true,
      workId: '',
      tableKey: 0,
      list: null,
      total: 0,
      limit: 20,
      page: 1,
      listLoading: true,
      statusOptions: [{
        key: '流程中',
        value: 'ONGOING'
      }, {
        key: '已完成',
        value: 'COMPLETED'
      }, {
        key: '未完成',
        value: 'INCOMPLETED'
      }],
      downloadLoading: false
    }
  },
  created() {
    // this.getList(1, 20, {})
    this.workId = this.$route.query.workId
    this.getList()
  },
  mounted() {
    // this.workId = this.$route.query.workId
  },
  methods: {
    getList() {
      this.listLoading = true
      getWorkOrderDetail(this.workId).then(response => {
        console.log('reponse', response)
        const workOrder = response.data.workOrderEntity
        this.basicInfo.workId = workOrder.workId
        this.basicInfo.account = workOrder.account
        this.basicInfo.userCn = workOrder.userCn
        this.basicInfo.email = workOrder.email
        this.basicInfo.createTime = parseTime(workOrder.createTime)
        if (workOrder.status === 'ONGOING') {
          this.basicInfo.status = '流程中'
        } else if (workOrder.status === 'COMPLETED') {
          this.basicInfo.status = '已完成'
        } else if (workOrder.status === 'INCOMPLETED') {
          this.basicInfo.status = '未完成'
        }
        // console.log(this.basicInfo)
        this.paramInfo.ipList = workOrder.ipList
        this.paramInfo.sysArgs = workOrder.sysArgs
        this.paramInfo.diamondContent = workOrder.diamondContent
        this.paramInfo.domain = workOrder.domain
        this.paramInfo.cdnDomain = workOrder.cdnDomain
        this.paramInfo.note = workOrder.note
        const appInfo = {}
        const app = response.data.appEntity
        appInfo.name = app.name
        appInfo.port = app.port
        appInfo.mem = app.mem
        appInfo.middleware = app.middleware
        appInfo.jdk = app.jdk
        if (app.sre === '1') {
          appInfo.sre = '是'
        } else {
          appInfo.sre = '否'
        }
        appInfo.note = app.note
        appInfo.code = app.code
        this.appInfoList.push(appInfo)
        const serverInfo = {}
        const server = response.data.serverEntity
        if (server.env === 'prd') {
          serverInfo.env = '生产'
        } else if (server.env === 'test') {
          serverInfo.env = '测试'
        } else {
          serverInfo.env = '其它'
        }
        serverInfo.region = server.region
        serverInfo.system = server.system
        serverInfo.cpuNum = server.cpuNum
        serverInfo.memNum = server.memNum
        serverInfo.diskSize = server.diskSize
        serverInfo.diskType = server.diskType
        serverInfo.num = server.num
        if (server.isNet === '0') {
          serverInfo.isNet = '否'
        } else {
          serverInfo.isNet = '是'
        }
        serverInfo.bastionDomain = server.bastionDomain
        if (server.status === 'SA_ONGOING') {
          serverInfo.status = 'SA流程中'
        } else if (server.status === 'COMPLETED') {
          serverInfo.status = '已完成'
        } else if (server.status === 'REJECTED') {
          serverInfo.status = '已驳回'
        }
        this.serverInfoList.push(serverInfo)
        // this.isSubmit = !(workOrder.status === 'ONGOING' && server.status === 'COMPLETED')
        if (server.ipList === null && workOrder.status === 'ONGOING') {
          this.isSubmit = false
        }
        if (server.ipList !== null && workOrder.status === 'ONGOING' && server.status === 'COMPLETED') {
          this.isSubmit = false
        }
        // Just to simulate the time of the request
        // console.log(this.isSubmit)
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    cellStyle(row, column, rowIndex, columnIndex) { // 根据报警级别显示颜色
      // console.log(row)
      if (row.column.label === '审批状态' && row.row.status === '已完成') {
        return 'color:green'
      } else if (row.column.label === '审批状态' && row.row.status === 'SA流程中') {
        return 'color:#EAC100'
      } else if (row.column.label === '审批状态' && row.row.status === '已驳回') {
        return 'color:red'
      }
    },
    submitSelect(value) {
      this.submitStatus = value
    },
    onSubmit() {
      this.popoverVisible = false
      const requestData = {}
      requestData.status = this.submitStatus
      requestData.content = this.submitContent
      requestData.workId = this.workId
      workOrderSubmit(requestData).then(response => {
        console.log(response)
        this.$message({
          type: 'success',
          message: '提交成功'
        })
      }).catch(err => {
        console.log(err)
        this.$message({
          type: 'error',
          message: '提交失败'
        })
      })
      console.log('submit!')
    },
    onClear() {
      this.popoverVisible = false
      this.submitContent = ''
      this.submitStatus = true
    }
  }
}
</script>
