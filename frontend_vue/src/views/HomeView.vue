<template>
  <div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">Add New</el-button>
    </div>

    <!-- Search -->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="Please input keyword" style="width: 30%"></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="list">Search</el-button>
    </div>


    <el-table :data="tableData" stripe style="width: 90%">
      <el-table-column
          prop="id"
          label="ID"
          sortable
      >
      </el-table-column>
      <el-table-column
          prop="name"
          label="Name"
      >
      </el-table-column>
      <el-table-column
          prop="maker"
          label="Maker">
      </el-table-column>

      <el-table-column
          prop="price"
          label="Price">
      </el-table-column>

      <el-table-column
          prop="sales"
          label="Sales">
      </el-table-column>

      <el-table-column
          prop="stock"
          label="Stock">
      </el-table-column>

      <el-table-column fixed="right" label="Operation" width="150">
        <!--        scope: Accessing the data of the current row -->
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text">Edit</el-button>
          <!--          Importing a confirmation dialog PopConfirm -->
          <el-popconfirm
              title="Confirm delete？" @confirm="handleDel(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="text">Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    Importing the pagination component - can be customized -->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <!-- Adding a furniture popup -->
    <!-- Explanation: -->
    <!-- 1. el-dialog: v-model="dialogVisible" represents the dialog, and it is bidirectionally bound to the variable dialogVisible, controlling whether the dialog is displayed -->
    <!-- 2. el-form: :model="form" represents bidirectional binding between form data and the form data variable -->
    <!-- 3. el-input v-model="form.name" represents an input control in the form, with the name 'name' needing to match the corresponding property in the backend JavaBean [Furn] -->
    <!--    This ensures that data is encapsulated when sent to the backend -->
    <el-dialog title="prompt" v-model="dialogVisible" width="30%">
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <!--        prop="name" indicates the association with the rule in 'rules' -->
        <el-form-item label="Name" prop="name">
          <el-input v-model="form.name" style="width: 50%"></el-input>
          <!--          Display the backend validation information returned - Vue's interpolation expression -->
          {{ validMsg.name }}
        </el-form-item>
        <el-form-item label="Maker" prop="maker">
          <el-input v-model="form.maker" style="width: 50%"></el-input>
          {{ validMsg.maker }}
        </el-form-item>
        <el-form-item label="Price" prop="price">
          <el-input v-model="form.price" style="width: 50%"></el-input>
          {{ validMsg.price }}
        </el-form-item>
        <el-form-item label="Sales" prop="sales">
          <el-input v-model="form.sales" style="width: 50%"></el-input>
          {{ validMsg.sales }}
        </el-form-item>
        <el-form-item label="Stock" prop="stock">
          <el-input v-model="form.stock" style="width: 50%"></el-input>
          {{ validMsg.stock }}
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="save">Confirm</el-button>
          </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
// @ is an alias to /src
// Importing the request component/object
import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      validMsg: {}, //Associated with backend validation information
      currentPage: 1,//current page
      pageSize: 5, //Number of records displayed per page
      total: 10, //Total number of records, obtained through a request to fetch the corresponding count from the table
      form: {}, //Form data
      dialogVisible: false,//Control whether the dialog is displayed, default is false
      search: '',
      tableData: [],
      rules: { //Validation rules for submitting the form
        name: [
          {required: true, message: 'please enter furniture name', trigger: 'blur'}
        ],
        maker: [
          {required: true, message: 'please enter maker', trigger: 'blur'}
        ],
        price: [
          {required: true, message: 'please enter price', trigger: 'blur'},
          // regular expression
          {pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/, message: 'please enter number', trigger: 'blur'}
        ],
        sales: [
          {required: true, message: 'please enter sales', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))$/, message: 'please enter number', trigger: 'blur'}
        ],
        stock: [
          {required: true, message: 'please enter stock', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))$/, message: 'please enter number', trigger: 'blur'}
        ]
      }
    }
  },
  created() { //call list() to display the table data
    this.list()
  },

  methods: {

    //handle pageSize change
    handlePageSizeChange(pageSize) {
      this.pageSize = pageSize
      this.list()
    },
    //handle currentPage change
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.list() //refresh list
    },
    //handle delete
    handleDel(id) {
      //Use the 'request' object to send a request to delete the current furniture
      request.delete("/api/del/" + id).then(res => {
        //Condition check
        if (res.code === "200") {//delete success
          this.$message({
            type: "success",
            message: "delete success"
          })
        } else { //delete fail
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
      //refresh list
      this.list()
    },

    list() {//Display furniture information
      //Pagination query with conditions
      request.get("/api/furnsBySearchPage", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        //Bind the returned data with 'tableData'
        this.tableData = res.data.records
        //update total
        this.total = res.data.total
      })
    },
    save() { //Add, Update

      if (this.form.id) { //update
        //Because request.put / get / post ... all use AJAX asynchronous request mechanism
        request.put("/api/update", this.form).then(
            res => {
              if (res.code === "200") { //update success
                //message box
                this.$message({
                  type: "success",
                  message: "Update Success"
                })
              } else { //update fail
                //Fail message box
                this.$message({
                  type: "error",
                  message: "Update Fail"
                })
              }
              //refresh list
              this.list()
              //hide dialog
              this.dialogVisible = false
            }
        )
      } else { //Add
        ///api/save corresponds to the actual request URL, which is http://localhost:9090/save
        //Here, when we add, it is associated with form validation. If the validation fails, it will not be submitted
        this.$refs['form'].validate((valid) => {
          if (valid) { //Front-end validation passed
            request.post("/api/save", this.form).then(
                res => {

                  if (res.code === "200") {
                    console.log("res=", res)
                    this.dialogVisible = false
                    this.list()
                  } else if (res.code === "400") {

                    //Retrieve the returned validation error information
                    this.validMsg.name = res.data.name
                    this.validMsg.maker = res.data.maker
                    this.validMsg.price = res.data.price
                    this.validMsg.sales = res.data.sales
                    this.validMsg.stock = res.data.stock

                  }


                }
            )
          } else {
            this.$message({
              type: "error",
              message: "Validation failed, do not submit"
            })
            return false;
          }
        })
      }
    },

    // <!-- 1. Send a Get request using row.id -->
    // <!-- 2. Receive the corresponding furniture information -->
    // <!-- 3. Extract the returned furniture information and bind it to this.form -->
    handleEdit(row) {
      request.get("/api/find/" + row.id).then(res => {
        if (res.code === "200") {
          this.form = res.data
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
      this.dialogVisible = true
    },
    //add method, display the dialog for adding
    add() {
      this.dialogVisible = true
      //Every time the dialog for adding is displayed, clear the form data
      this.form = {}
      //Clear the information from the previous form validation
      if(this.$refs['form']){
        this.$refs['form'].resetFields()
      }
      //Clear the validation information returned from the backend from the previous time
      this.validMsg = {}
    }
  }
}
</script>
