<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="query.workId" placeholder="工单号" style="width: 200px;" class="filter-item" @change="workIdSelect" />
      <el-input v-model="query.account" placeholder="申请人" style="width: 200px;" class="filter-item" @change="accountSelect" />
      <el-select v-model="query.status" placeholder="状态" clearable style="width: 90px" class="filter-item" @change="statusSelect">
        <el-option v-for="item in statusOptions" :key="item.value" :label="item.key" :value="item.value" />
      </el-select>
      <el-button v-waves class="pan-btn light-blue-btn" type="primary" icon="el-icon-search" @click="handleFilter" style="margin-left:25px">
        Search
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="pan-btn light-blue-btn" type="primary" icon="el-icon-download" @click="handleDownload">
        Export
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="工单ID" prop="workId" align="center">
        <template slot-scope="{row}">
          <span class="link-type" @click="handleWorkId(row)">{{ row.workId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请人" prop="account" align="center">
      </el-table-column>
      <el-table-column label="状态" class-name="status-col" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" align="center">
      </el-table-column>
      <el-table-column label="备注" prop="note">
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="page" :limit.sync="limit" @pagination="pageChange" />
  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import { getWorkOrderList } from '@/api/haiyanData'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
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
      query: {
        workId: '',
        account: '',
        status: ''
      },
      tableKey: 0,
      list: null,
      total: 0,
      limit: 10,
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
    this.getList(1, 10, {})
  },
  methods: {
    getList(pageNo, pageSize, data) {
      this.listLoading = true
      getWorkOrderList(pageNo, pageSize, data).then(response => {
        // console.log('reponse', response)
        this.list = response.data.list
        this.total = response.data.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    statusSelect(value) {
      this.query.status = value
    },
    workIdSelect(value) {
      this.query.workId = value
    },
    accountSelect(value) {
      this.query.account = value
    },
    pageChange(data) {
      this.page = data.page
      this.limit = data.limit
      this.getList(this.page, this.limit, this.query)
    },
    handleWorkId(row) {
      // console.log(row.workId)
       this.$router.push({ path:'/hy-data/detail',query: {   
                workId: row.workId,   
            }})
    },
    handleFilter() {
      this.getList(1, 10, this.query)
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['workId', 'account', 'status', 'createTime', 'note']
        const filterVal = ['workId', 'account', 'status', 'createTime', 'note']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    }
  }
}
</script>
