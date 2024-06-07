import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from './components/Home';
import VehicleList from './components/VehicleList';
import AddEditVehicle from './components/AddEditVehicle';
import RepairList from './components/RepairList'
import AddEditRepair from './components/AddEditRepair';
import RepairDetails from './components/RepairDetails';
import NotFound from './components/NotFound';
import Summary2 from './components/Summary2'
import BrandDiscountList from './components/BrandDiscountList';
import AddBrandDiscount from './components/AddBrandDiscount';
import RepairTypeList from './components/RepairTypeList';
import AddEditRepairType from './components/AddEditRepairType';
import Summary1 from './components/Summary1';

const App = () => {
  return (
    <Router>
      <div>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/vehicle/list" element={<VehicleList />} />
          <Route path="/vehicle/add" element={<AddEditVehicle />} />
          <Route path="/vehicle/edit/:id" element={<AddEditVehicle />} />
          <Route path="/repair/list" element={<RepairList />} />
          <Route path="/repair/add" element={<AddEditRepair />} />
          <Route path="/repair/edit/:id" element={<AddEditRepair />} />
          <Route path="/repair/details/:id" element={<RepairDetails />} />
          <Route path="/repairType/list" element={<RepairTypeList />} />
          <Route path="/repairType/add" element={<AddEditRepairType />} />
          <Route path="/repairType/edit/:id" element={<AddEditRepairType />} />
          <Route path="/brandDiscount/list" element={<BrandDiscountList />} />
          <Route path="/brandDiscount/add" element={<AddBrandDiscount />} />
          <Route path="/summaries/summary1" element={<Summary1 />} />
          <Route path="/summaries/summary2" element={<Summary2 />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};


export default App
