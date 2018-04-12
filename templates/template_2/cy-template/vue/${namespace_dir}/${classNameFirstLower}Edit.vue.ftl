<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <#list table.optionsColumns as column>
      <el-form-item label="${column.remarks}" prop="${column.columnNameLower}">
        <el-input v-model${column.number?string(".number","")}="dataForm.${column.columnNameLower}" placeholder="${column.remarks}"></el-input>
      </el-form-item>
      </#list>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import * as ${className} from '@/api/modules/${namespace}/${classNameLower}'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
        },
        dataRule: {
        <#list table.optionsColumns as column>
          ${column.columnNameLower}: ${column.elementUIDataRule}<#if column_has_next>,</#if>
        </#list>
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            ${className}.info(this.dataForm.id).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm = data.obj
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            var tick = !this.dataForm.id ? ${className}.add(this.dataForm) : ${className}.update(this.dataForm)
            tick.then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
