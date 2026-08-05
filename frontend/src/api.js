import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: { 'Content-Type': 'application/json' },
})

export function parseError(err) {
  const data = err?.response?.data
  if (data?.fieldErrors && Object.keys(data.fieldErrors).length > 0) {
    return {
      message: data.message || 'Please correct the highlighted fields.',
      fieldErrors: data.fieldErrors,
    }
  }
  if (data?.message) {
    return { message: data.message, fieldErrors: {} }
  }
  if (err?.code === 'ERR_NETWORK') {
    return {
      message: 'Cannot reach the server. Is the Spring Boot backend running on port 8080?',
      fieldErrors: {},
    }
  }
  return { message: err?.message || 'Something went wrong.', fieldErrors: {} }
}

const body = (res) => res.data

export const SERVER_URL = 'http://localhost:8080'

export function vehicleImageUrl(vehicle) {
  return vehicle?.imagePath ? `${SERVER_URL}/uploads/${vehicle.imagePath}` : null
}

export function customerImageUrl(customer) {
  return customer?.imagePath ? `${SERVER_URL}/uploads/${customer.imagePath}` : null
}

export const authApi = {
  signup: (data) => http.post('/auth/signup', data).then(body),
  login: (data) => http.post('/auth/login', data).then(body),
}

export const customerApi = {
  list: (search) => http.get('/customers', { params: search ? { search } : {} }).then(body),
  get: (id) => http.get(`/customers/${id}`).then(body),
  
  update: (id, data) => http.put(`/customers/${id}`, data).then(body),
  
  uploadPhoto: (id, file) => {
    const form = new FormData()
    form.append('file', file)
    return http.post(`/customers/${id}/photo`, form, {
      headers: { 'Content-Type': undefined },
    }).then(body)
  },
  removePhoto: (id) => http.delete(`/customers/${id}/photo`).then(body),
  remove: (id) => http.delete(`/customers/${id}`).then(body),
  count: () => http.get('/customers/count').then(body),
}

export const categoryApi = {
  list: (search) => http.get('/categories', { params: search ? { search } : {} }).then(body),
  get: (id) => http.get(`/categories/${id}`).then(body),
  create: (data) => http.post('/categories', data).then(body),
  update: (id, data) => http.put(`/categories/${id}`, data).then(body),
  remove: (id) => http.delete(`/categories/${id}`).then(body),
  count: () => http.get('/categories/count').then(body),
}

export const vehicleApi = {
  
  list: ({ categoryId, status } = {}) => {
    const params = {}
    if (categoryId) params.categoryId = categoryId
    if (status) params.status = status
    return http.get('/vehicles', { params }).then(body)
  },
  search: (q) => http.get('/vehicles/search', { params: { q } }).then(body),
  get: (id) => http.get(`/vehicles/${id}`).then(body),
  create: (data) => http.post('/vehicles', data).then(body),
  update: (id, data) => http.put(`/vehicles/${id}`, data).then(body),
  setStatus: (id, status) => http.patch(`/vehicles/${id}/status`, null, { params: { status } }).then(body),
  
  uploadPhoto: (id, file) => {
    const form = new FormData()
    form.append('file', file)
    return http.post(`/vehicles/${id}/photo`, form, {
      headers: { 'Content-Type': undefined },
    }).then(body)
  },
  removePhoto: (id) => http.delete(`/vehicles/${id}/photo`).then(body),
  remove: (id) => http.delete(`/vehicles/${id}`).then(body),
  count: (status) => http.get('/vehicles/count', { params: status ? { status } : {} }).then(body),
}

export const driverApi = {
  list: (available) =>
    http.get('/drivers', { params: available === undefined || available === '' ? {} : { available } }).then(body),
  search: (q) => http.get('/drivers/search', { params: { q } }).then(body),
  get: (id) => http.get(`/drivers/${id}`).then(body),
  create: (data) => http.post('/drivers', data).then(body),
  update: (id, data) => http.put(`/drivers/${id}`, data).then(body),
  setAvailability: (id, available) =>
    http.patch(`/drivers/${id}/availability`, null, { params: { available } }).then(body),
  remove: (id) => http.delete(`/drivers/${id}`).then(body),
  count: () => http.get('/drivers/count').then(body),
}

export const bookingApi = {
  list: ({ status, from, to } = {}) => {
    const params = {}
    if (status) params.status = status
    if (from && to) { params.from = from; params.to = to }
    return http.get('/bookings', { params }).then(body)
  },
  get: (id) => http.get(`/bookings/${id}`).then(body),
  byCustomer: (customerId) => http.get(`/bookings/customer/${customerId}`).then(body),
  byVehicle: (vehicleId) => http.get(`/bookings/vehicle/${vehicleId}`).then(body),
  create: (data) => http.post('/bookings', data).then(body),
  update: (id, data) => http.put(`/bookings/${id}`, data).then(body),
  setStatus: (id, status) => http.patch(`/bookings/${id}/status`, null, { params: { status } }).then(body),
  remove: (id) => http.delete(`/bookings/${id}`).then(body),
  count: (status) => http.get('/bookings/count', { params: status ? { status } : {} }).then(body),
}

export const paymentApi = {
  list: () => http.get('/payments').then(body),
  get: (id) => http.get(`/payments/${id}`).then(body),
  byBooking: (bookingId) => http.get(`/payments/booking/${bookingId}`).then(body),
  balance: (bookingId) => http.get(`/payments/booking/${bookingId}/balance`).then(body),
  create: (data) => http.post('/payments', data).then(body),
  update: (id, data) => http.put(`/payments/${id}`, data).then(body),
  remove: (id) => http.delete(`/payments/${id}`).then(body),
  count: () => http.get('/payments/count').then(body),
}

export const VEHICLE_STATUSES = ['AVAILABLE', 'RENTED', 'MAINTENANCE']
export const FUEL_TYPES = ['PETROL', 'DIESEL', 'HYBRID', 'ELECTRIC']
export const TRANSMISSIONS = ['MANUAL', 'AUTOMATIC']
export const BOOKING_STATUSES = ['PENDING', 'ACTIVE', 'COMPLETED', 'CANCELLED']
export const PAYMENT_METHODS = ['CASH', 'CARD', 'BANK_TRANSFER']
export const PAYMENT_TYPES = ['ADVANCE', 'FULL', 'BALANCE']

export function money(value) {
  const n = Number(value ?? 0)
  return 'Rs. ' + n.toLocaleString('en-LK', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
}

export function statusClass(status) {
  switch (status) {
    case 'AVAILABLE':
    case 'COMPLETED':
      return 'badge badge-green'
    case 'RENTED':
    case 'ACTIVE':
      return 'badge badge-blue'
    case 'MAINTENANCE':
    case 'PENDING':
      return 'badge badge-amber'
    case 'CANCELLED':
      return 'badge badge-red'
    default:
      return 'badge badge-grey'
  }
}

export default http
