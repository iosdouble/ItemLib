<style rel="stylesheet/scss" lang="scss" scoped>
.container{
  padding: 32px;
  background-color: rgba(0,0,0,0);
  position: relative;
  width: 100%;
  height: 100%;
  .medium-container{
    padding-top: 25px;
    .medium-word-container{
      padding-bottom: 25px;
    }
  }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
}
</style>

<template>
  <div class="container">
    <div class="top-container">
    <el-row>
      <font size="4" color="#409EFF">工单时长查询</font>
    </el-row>
    <el-row>
      <el-col :span="6">
      开始时间：
      <el-select v-model="startTime" filterable placeholder="请选择" @change="startSelect" >
      <el-option
        v-for="item in startOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
      </el-select>
      </el-col>
      <el-col :span="6">
      结束时间：
      <el-select v-model="endTime" filterable placeholder="请选择" @change="endSelect" >
      <el-option
        v-for="item in endOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
      </el-select>
      </el-col>
      <el-button type="primary" @click="onSubmit" style="margin-left:330px">查询</el-button>
    </el-row>
    </div>
    <div class="medium-container">
      <div class="medium-word-container">
      <el-card shadow="always">
        {{ word }}
      </el-card>
      </div>
      <v-chart theme="macarons" :options="polar" style="width:1100px" />
    </div>
    <div class = "bottom-container">
      <div class = "bottom-select-containter">
        <el-row>
          <font size="4" color="#409EFF">工单时长环比数据</font>
        </el-row>
        <el-row>
          <el-col :span="7">
          本周/月起：
          <el-select v-model="ratioStartDate" filterable placeholder="请选择" @change="ratioStartSelect" >
          <el-option
            v-for="item in startOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>
          </el-col>
          <el-col :span="7">
          止：
          <el-select v-model="ratioEndDate" filterable placeholder="请选择" @change="ratioEndSelect" >
          <el-option
            v-for="item in endOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="7">
          上周/月起：
          <el-select v-model="ratioLastStartDate" filterable placeholder="请选择" @change="ratioLastStartSelect" >
          <el-option
            v-for="item in startOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>
          </el-col>
          <el-col :span="4.5">
          止：
          <el-select v-model="ratioLastEndDate" filterable placeholder="请选择" @change="ratioLastEndSelect" >
          <el-option
            v-for="item in endOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
          </el-select>
          </el-col>
          <el-button type="primary" @click="onRatioSubmit" style="margin-left:330px">查询</el-button>
        </el-row>
        <el-table
          :data="tableData"
          style="width: 100%"
          :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column
            prop="quota"
            label="指标"
            width="350"
          >
          </el-table-column>
          <el-table-column
            prop="avgDeliver"
            label="平均处理时长"
          >
          </el-table-column>
        </el-table>
      </div>
      <div class = "bottom-analyse-containter" style="margin-top:30px">
      <el-row>
        <font size="4" color="#409EFF">时长分析</font>
      </el-row>
      <el-row>
        <el-col :span="6">
        开始时间：
        <el-select v-model="analyseStartTime" filterable placeholder="请选择" @change="timeStartSelect" >
        <el-option
          v-for="item in startOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
        </el-select>
        </el-col>
        <el-col :span="6">
        结束时间：
        <el-select v-model="analyseEndTime" filterable placeholder="请选择" @change="timeEndSelect" >
        <el-option
          v-for="item in endOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
        </el-select>
        </el-col>
        <el-button type="primary" @click="onTimeAnalyseSubmit" style="margin-left:330px">查询</el-button>
      </el-row>
      <el-row>
        <el-card shadow="always">
          {{ analyseWord }}
        </el-card>
      </el-row>
      <el-row>
      模块工单时长1-5小时：
      </el-row>
      <el-row>
        <el-table
          :data="bList"
          style="width: 100%"
          :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column
            prop="deliverTime"
            label="交付处理时长(h)"
            width="80px"
          >
          </el-table-column>
          <el-table-column
            prop="id"
            label="工单号"
          >
          </el-table-column>
          <el-table-column
            prop="applyType"
            label="模块名"
          >
          </el-table-column>
          <el-table-column
            prop="applyTypeName"
            label="数据库字段"
          >
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
          >
          </el-table-column>
          <el-table-column
            prop="applyStatus"
            label="状态代码"
            width="50px"
          >
          </el-table-column>
          <el-table-column
            prop="applyDateTime"
            label="创建时间"
          >
          </el-table-column>
          <el-table-column
            prop="approvalTime"
            label="最后一次审批时间"
          >
          </el-table-column>
          <el-table-column
            prop="doneTime"
            label="完成时间"
          >
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
      模块工单时长5-10小时：
      </el-row>
      <el-row>
        <el-table
          :data="cList"
          style="width: 100%"
          :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column
            prop="deliverTime"
            label="交付处理时长(h)"
            width="80px"
          >
          </el-table-column>
          <el-table-column
            prop="id"
            label="工单号"
          >
          </el-table-column>
          <el-table-column
            prop="applyType"
            label="模块名"
          >
          </el-table-column>
          <el-table-column
            prop="applyTypeName"
            label="数据库字段"
          >
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
          >
          </el-table-column>
          <el-table-column
            prop="applyStatus"
            label="状态代码"
            width="50px"
          >
          </el-table-column>
          <el-table-column
            prop="applyDateTime"
            label="创建时间"
          >
          </el-table-column>
          <el-table-column
            prop="approvalTime"
            label="最后一次审批时间"
          >
          </el-table-column>
          <el-table-column
            prop="doneTime"
            label="完成时间"
          >
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
      模块工单时长10小时以上：
      </el-row>
      <el-row>
        <el-table
          :data="dList"
          style="width: 100%"
          :header-cell-style="{background:'#eef1f6',color:'#606266'}">
          <el-table-column
            prop="deliverTime"
            label="交付处理时长(h)"
            width="80px"
          >
          </el-table-column>
          <el-table-column
            prop="id"
            label="工单号"
          >
          </el-table-column>
          <el-table-column
            prop="applyType"
            label="模块名"
          >
          </el-table-column>
          <el-table-column
            prop="applyTypeName"
            label="数据库字段"
          >
          </el-table-column>
          <el-table-column
            prop="status"
            label="状态"
          >
          </el-table-column>
          <el-table-column
            prop="applyStatus"
            label="状态代码"
            width="50px"
          >
          </el-table-column>
          <el-table-column
            prop="applyDateTime"
            label="创建时间"
          >
          </el-table-column>
          <el-table-column
            prop="approvalTime"
            label="最后一次审批时间"
          >
          </el-table-column>
          <el-table-column
            prop="doneTime"
            label="完成时间"
          >
          </el-table-column>
        </el-table>
      </el-row>
    </div>
    </div>
  </div>
</template>
<script>
import ECharts from 'vue-echarts/components/ECharts'
import 'echarts'
import 'echarts/theme/macarons.js'
import { getWorkTimeAnalyse, getWorkTimeRatio, getWorkTimeAnalyseData } from '@/api/weekly'
import { start, end } from '../../contant'
export default {
  name: 'WorkTime',
  components: {
    'v-chart': ECharts
  },
  data() {
    return {
      polar: {},
      startOptions: start,
      startTime: '',
      endOptions: end,
      ratioStart: start,
      endTime: '',
      word: '',
      ratioStartDate: '',
      ratioEndDate: '',
      ratioLastStartDate: '',
      ratioLastEndDate: '',
      startWord: '',
      endWord: '',
      lastStartWord: '',
      lastEndWord: '',
      tableData: [],
      analyseStartTime: '',
      analyseEndTime: '',
      analyseWord: '',
      bList: [],
      cList: [],
      dList: []
    }
  },
  created() {
    // this.initData()
  },
  methods: {
    initData(time) {
      const deliverList = []
      const avgDeliverList = []
      const aList = []
      const dList = []
      const timeList = []
      getWorkTimeAnalyse(time).then(response => {
        // 初始化word;
        const vo = response.data.workTimeWordVO
        this.word = `平均处理时长${vo.avgDeliver}小时。
        ${vo.minTime}处理时长最少为${vo.minDeliver}小时，${vo.maxTime}处理时长最长为${vo.maxDeliver}小时。`
        this.chartVOList = response.data.chartVOList
        for (const x of this.chartVOList) {
          deliverList.push(x.deliver)
          avgDeliverList.push(x.avgDeliver)
          aList.push(x.a)
          dList.push(x.d)
          timeList.push(x.time)
        }
        this.$message({
          type: 'success',
          message: '查询成功'
        })
      }).catch(err => {
        console.log(err)
        this.$message({
          type: 'error',
          message: '查询失败'
        })
      })
      this.polar = {
        title: {
          text: '工单时长数据统计',
          subtext: '动态数据',
          x: 'top'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['平均处理时长', 'A', 'D', '均值']
        },
        xAxis: [
          {
            type: 'category',
            data: timeList,
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '个',
            min: 0
          }
        ],
        series: [
          {
            name: '平均处理时长',
            type: 'bar',
            data: deliverList,
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          },
          {
            name: 'A',
            type: 'line',
            markPoint: {
              data: [{
                name: '最大值',
                type: 'max'
              }]
            },
            data: aList
          },
          {
            name: 'D',
            type: 'line',
            markPoint: {
              data: [{
                name: '最大值',
                type: 'max'
              }]
            },
            data: dList
          },
          {
            name: '均值',
            type: 'line',
            markPoint: {
              data: [{
                name: '最大值',
                type: 'max'
              }]
            },
            data: avgDeliverList
            // label: {
            //   normal: {
            //     show: true,
            //     position: 'top'
            //   }
            // }
          }
        ]
      }
    },
    startSelect(value) {
      this.startTime = value
    },
    endSelect(value) {
      this.endTime = value
    },
    onSubmit() {
      const time = {}
      time.startDate = this.startTime
      time.endDate = this.endTime
      this.initData(time)
    },
    findStartLabel(value) {
      let obj = {}
      obj = start.find((item) => {
        return item.value === value
      })
      return obj.label
    },
    findEndLabel(value) {
      let obj = {}
      obj = end.find((item) => {
        return item.value === value
      })
      return obj.label
    },
    ratioStartSelect(value) {
      this.ratioStartDate = value
      const label = this.findStartLabel(value)
      this.startWord = label
    },
    ratioEndSelect(value) {
      this.ratioEndDate = value
      const label = this.findEndLabel(value)
      this.endWord = label
    },
    ratioLastStartSelect(value) {
      this.ratioLastStartDate = value
      const label = this.findStartLabel(value)
      this.lastStartWord = label
    },
    ratioLastEndSelect(value) {
      this.ratioLastEndDate = value
      const label = this.findEndLabel(value)
      this.lastEndWord = label
    },
    onRatioSubmit() {
      const data = {}
      data.startDate = this.ratioStartDate
      data.endDate = this.ratioEndDate
      data.word = this.startWord + '～' + this.endWord
      data.lastStartDate = this.ratioLastStartDate
      data.lastEndDate = this.ratioLastEndDate
      data.lastWord = this.lastStartWord + '～' + this.lastEndWord
      getWorkTimeRatio(data).then(response => {
        this.tableData = response.data
        this.$message({
          type: 'success',
          message: '查询成功'
        })
      }).catch(err => {
        console.log(err)
        this.$message({
          type: 'error',
          message: '查询失败'
        })
      })
    },
    timeStartSelect(value) {
      this.analyseStartTime = value
    },
    timeEndSelect(value) {
      this.analyseEndTime = value
    },
    onTimeAnalyseSubmit() {
      const data = {}
      data.startDate = this.analyseStartTime
      data.endDate = this.analyseEndTime
      getWorkTimeAnalyseData(data).then(response => {
        this.analyseWord = response.data.word
        this.bList = response.data.blist
        this.cList = response.data.clist
        this.dList = response.data.dlist
        this.$message({
          type: 'success',
          message: '查询成功'
        })
      }).catch(err => {
        console.log(err)
        this.$message({
          type: 'error',
          message: '查询失败'
        })
      })
    }
  }
}
</script>
