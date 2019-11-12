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
      <font size="4" color="#409EFF">工单交付查询</font>
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
      <v-chart theme="macarons" :options="polar" style="width:550px" />
    </div>
    <div class = "bottom-container">
      <div class = "bottom-select-containter">
        <el-row>
          <font size="4" color="#409EFF">工单交付环比数据</font>
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
            prop="deliverCount"
            label="交付工单（个)"
          >
          </el-table-column>
          <el-table-column
            prop="failRatio"
            label="失败率"
          >
          </el-table-column>
          <el-table-column
            prop="endDeliver"
            label="截止交付工单（个）"
          >
          </el-table-column>
          <el-table-column
            prop="endFailRatio"
            label="截止失败率"
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
import { getWorkDeliverAnalyse, getWorkDeliverRatio } from '@/api/weekly'
import { start, end } from '../../contant'
export default {
  name: 'WorkDeliver',
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
      const completeCountList = []
      const deliverCountList = []
      const deliverAvgList = []
      const timeList = []
      getWorkDeliverAnalyse(time).then(response => {
        // 初始化word;
        const deliverWord = response.data.workDeliverWordVO
        this.word = `平均每周完成工单${deliverWord.avgComplete}张，交付工单${deliverWord.avgDeliver}张，平均交付率${deliverWord.avgDeliverRatio}。
        交付工单中${deliverWord.minTime}最少为${deliverWord.minCount}单，${deliverWord.maxTime}达到${deliverWord.maxCount}单。`
        this.chartVOList = response.data.chartVOList
        for (const x of this.chartVOList) {
          completeCountList.push(x.completeCount)
          deliverCountList.push(x.deliverCount)
          deliverAvgList.push(x.deliverAvg)
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
          text: '工单交付数据统计',
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
        // color: ['#9b8bba', '#fad860', '#8fd3e8'],
        legend: {
          data: ['完成', '交付', '交付工单平均值']
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
            name: '完成',
            type: 'bar',
            data: completeCountList,
            label: {
              normal: {
                show: true,
                position: 'top',
                textStyle: {
                  fontSize: 15
                }
              }
            }
          },
          {
            name: '交付',
            type: 'line',
            color: '#B15BFF',
            data: deliverCountList,
            // lineStyle: {
            //   normal: {
            //     color: '#e098c7'
            //   }
            // },
            label: {
              normal: {
                show: true,
                position: 'top',
                textStyle: {
                  fontSize: 15
                }
              }
            }
          },
          {
            name: '交付工单平均值',
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
            data: deliverAvgList
            // label: {
            //   normal: {
            //     show: true,
            //     textStyle: {
            //       fontSize: 18
            //     }
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
      getWorkDeliverRatio(data).then(response => {
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
