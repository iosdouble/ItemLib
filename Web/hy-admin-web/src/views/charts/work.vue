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
      <font size="4" color="#409EFF">工单量查询</font>
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
          <font size="4" color="#409EFF">工单环比数据</font>
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
            prop="insertCount"
            label="新增工单（个)"
          >
          </el-table-column>
          <el-table-column
            prop="endInsert"
            label="截止新增工单（个）"
          >
          </el-table-column>
          <el-table-column
            prop="completeCount"
            label="完成工单（个）"
          >
          </el-table-column>
          <el-table-column
            prop="endComplete"
            label="截止完成工单（个）"
          >
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
import ECharts from 'vue-echarts/components/ECharts'
import 'echarts'
import 'echarts/theme/macarons.js'
import { getWorkAnalyse, getWorkRatio } from '@/api/weekly'
import { start, end } from '../../contant'
export default {
  name: 'Work',
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
      tableData: []
    }
  },
  created() {
    // this.initData()
  },
  methods: {
    initData(time) {
      const insertCountList = []
      const completeCountList = []
      const completeAvgList = []
      const timeList = []
      getWorkAnalyse(time).then(response => {
        // 初始化word;
        const workWord = response.data.workWordVO
        const avgInsert = workWord.avgInsert
        const avgComplete = workWord.avgComplete
        const avgCompleteRatio = workWord.avgCompleteRatio
        this.word = `平均每周新增工单${avgInsert} 张。
        平均每周完成工单${avgComplete}张。
        平均周完成率${avgCompleteRatio}。`
        this.chartVOList = response.data.chartVOList
        for (const x of this.chartVOList) {
          insertCountList.push(x.insertCount)
          completeCountList.push(x.completeCount)
          completeAvgList.push(x.completeAvg)
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
          text: '工单数据统计',
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
        // color: ['#9b8bba', '#FFD306', '#8fd3e8'],
        legend: {
          data: ['新增', '完成工单', '完成工单平均值']
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
            name: '新增',
            type: 'bar',
            data: insertCountList,
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          },
          {
            name: '完成工单',
            type: 'line',
            data: completeCountList,
            color: '#B15BFF',
            // lineStyle: {
            //   normal: {
            //     color: '#FFD306'
            //   }
            // },
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          },
          {
            name: '完成工单平均值',
            type: 'line',
            color: '#ffb980',
            // lineStyle: {
            //   normal: {
            //     color: '#8fd3e8'
            //   }
            // },
            markPoint: {
              data: [{
                name: '最大值',
                type: 'max'
              }]
            },
            data: completeAvgList
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
      getWorkRatio(data).then(response => {
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
    }
  }
}
</script>
