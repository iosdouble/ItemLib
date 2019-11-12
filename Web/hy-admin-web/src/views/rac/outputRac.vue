<style rel="stylesheet/scss" lang="scss" scoped>
  .container {
    padding: 32px;
    background-color: rgba(0, 0, 0, 0);
    position: relative;
    width: 100%;
    height: 100%;

    p {
      color: red;
    }

    .outputBtn {
      margin: 10px;
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
  }
</style>

<template>
  <div class="container">

    <el-button class="outputBtn" type="primary" @click="exportExcel">点击导出</el-button>

    <el-card class="box-app">
      <div slot="header" class="clearfix">
        <span>故障清单</span>
        <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
      </div>
      <div>
        <!--    <el-table :data="tableData"  border style="width: 100%" id="out-table">-->
        <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                  border
                  style="width: 100%"
                  :header-cell-style="{background:'#eef1f6',color:'#606266'}"
                  id="out-table">
          <el-table-column
            prop="date"
            label="序列号"
          >
          </el-table-column>

          <el-table-column
            prop="name"
            label="故障编号"
          >
          </el-table-column>
          <el-table-column
            prop="province"
            label="主题描述"
          >
          </el-table-column>
          <el-table-column
            prop="city"
            label="等级"
          >
          </el-table-column>
          <el-table-column
            prop="address"
            label="归属"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="逻辑层"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="涉及部门"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="涉及业务"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="故障开始时间"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="故障定位时间"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="故障结束时间"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="定位时长"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="故障时长"
          >
          </el-table-column>
          <el-table-column
            prop="zip"
            label="改进时间"
          >
          </el-table-column>

          <el-table-column fixed="right" label="操作">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              <el-button type="text" size="small">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
    <!--给按钮绑定事件-->


    <!-- 分页器 -->
    <div class="block" style="margin-top:15px;">
      <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="currentPage" :page-sizes="[5,10,15,50]" :page-size="pageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="tableData.length">
      </el-pagination>
    </div>

  </div>
</template>

<script>
  // 引入导出Excel表格依赖
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'

  export default {
    methods: {
      handleClick(row) {
        alert(row)
        console.log(row)
      }
      ,
      exportExcel() {
        /* 从表生成工作簿对象 */
        console.log('触发的导出事件')
        var wb = XLSX.utils.table_to_book(document.querySelector('#out-table'))
        /* 获取二进制字符串作为输出 */
        var wbout = XLSX.write(wb, {
          bookType: 'xlsx',
          bookSST: true,
          type: 'array'
        })
        try {
          FileSaver.saveAs(
            //Blob 对象表示一个不可变、原始数据的类文件对象。
            //Blob 表示的不一定是JavaScript原生格式的数据。
            //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
            //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
            new Blob([wbout], {
              type: 'application/octet-stream'
            }),
            //设置导出文件名称
            'sheetjs.xlsx'
          )
        } catch (e) {
          if (typeof console !== 'undefined') console.log(e, wbout)
        }
        return wbout
      }
      ,
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`)
        this.currentPage = 1
        this.pageSize = val
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`)
        this.currentPage = val
      }
    },

    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1517 弄',
          zip: 200333
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1519 弄',
          zip: 200333
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          },
          {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1516 弄',
            zip: 200333
          }
        ],
        currentPage: 1, // 当前页码
        total: 80, // 总条数
        pageSize: 5 // 每页的数据条数

      }
    }

  }
</script>

<style>
</style>
