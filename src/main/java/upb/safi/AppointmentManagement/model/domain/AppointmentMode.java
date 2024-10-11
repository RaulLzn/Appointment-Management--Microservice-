package upb.safi.AppointmentManagement.model.domain;

/**
 * AppointmentMode
 *
 * This enum represents the different modes in which an appointment can be conducted.
 * It provides a clear distinction between the type of appointment based on the format
 * in which it is held. The modes defined in this enum are:
 *
 * <ul>
 *     <li><b>PRESENCIAL</b>: Represents an in-person appointment.</li>
 *     <li><b>VIRTUAL</b>: Represents an appointment conducted remotely via digital means.</li>
 * </ul>
 */
public enum AppointmentMode {
    /**
     * Represents an in-person appointment.
     * This mode is typically used when the parties involved meet face-to-face.
     */
    PRESENCIAL,

    /**
     * Represents a virtual appointment.
     * This mode is used for appointments conducted through online platforms or video conferencing tools.
     */
    VIRTUAL
}
